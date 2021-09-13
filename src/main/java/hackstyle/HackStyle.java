package hackstyle;

import hackstyle.gui.InternetTab;
import hackstyle.gui.MainTab;
import hackstyle.gui.SettingsTab;
import hackstyle.keyboard.Keyboard;
import hackstyle.keyboard.KeyboardUtils;
import hackstyle.scripts.*;
import hackstyle.scripts.exceptions.ScriptParsingException;
import hackstyle.settings.HackStyleSettings;
import hackstyle.settings.HackStyleSettingsReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HackStyle extends Application {

    private static final String DEFAULTS_FILE = "/default-settings.xml";
    private static final String SETTINGS_FILE = "HackStyleSettings.xml";

    public static void main(String[] args) {
        launch(args);
    }

    private static HackStyleSettings readSettings() {
        try {
            return HackStyleSettingsReader.read(HackStyle.SETTINGS_FILE, HackStyle.DEFAULTS_FILE);
        } catch (JAXBException | IOException e) {
            ErrorMessage.createErrorFile(e);
            e.printStackTrace();
        }
        return null;
    }

    private static List<HackStyleScript> readScripts(HackStyleSettings settings, HackStyleScriptFactory scriptFactory) {
        try {
            final List<HackStyleScript> scripts = new ArrayList<>(settings.scripts.size());
            for (HackStyleSettings.Script scriptSettings : settings.scripts) {
                scripts.add(scriptFactory.createScript(scriptSettings));
            }
            return scripts;
        } catch (ScriptParsingException e) {
            ErrorMessage.createErrorFile(e);
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void start(Stage primaryStage) {
        final HackStyleSettings settings = readSettings();
        if (settings == null) {
            System.exit(-1);
        }

        final TabPane gui = new TabPane();
        final MainTab mainTab = new MainTab();

        final String defaultTextField = settings.values.stream()
                .filter(value -> value.name.equals("DEFAULT-TEXTFIELD"))
                .findFirst().map(value -> value.content).orElse("");
        mainTab.getTextField().setText(defaultTextField);

        final int defaultSlider = settings.values.stream()
                .filter(value -> value.name.equals("DEFAULT-SLIDER"))
                .findFirst().map(value -> Integer.parseInt(value.content)).orElse(5);
        mainTab.getScrollBar().setValue(defaultSlider);

        gui.getTabs().add(mainTab);
        gui.getTabs().add(new InternetTab());
        gui.getTabs().add(new SettingsTab(settings));

        final HackStyleScriptFactory scriptFactory = new HackStyleScriptFactory(settings.values);

        final List<HackStyleScript> scripts = readScripts(settings, scriptFactory);

        final HackStyleScriptRunner scriptRunner = new HackStyleScriptRunner(
                mainTab.getScrollBar().valueProperty(),
                mainTab.getTextField().textProperty()
        );

        scripts.forEach(mainTab::addScript);

        Keyboard.init();
        Keyboard.onKeyPress().perform(event -> {
            for (HackStyleScript script : mainTab.getActiveScripts().get()) {
                if (KeyboardUtils.parseCharToKey(script.indicator()) == event.getKeyCode()) {
                    scriptRunner.run(script);
                }
            }
        });
        Keyboard.onKeyRelease().perform(event -> {
            for (HackStyleScript script : mainTab.getActiveScripts().get()) {
                if (KeyboardUtils.parseCharToKey(script.indicator()) == event.getKeyCode()) {
                    script.stop();
                }
            }
        });

        final Scene scene = new Scene(gui, 700, 300);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HackStyle by Style");
        primaryStage.setOnCloseRequest(event -> {
            Keyboard.destroy();
            Platform.exit();
            scriptRunner.dispose();
            try {
                HackStyleSettingsReader.save(HackStyle.SETTINGS_FILE, settings);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });
        primaryStage.show();

        if (!GlobalScreen.isNativeHookRegistered()) {
            System.exit(0);
        }
    }
}