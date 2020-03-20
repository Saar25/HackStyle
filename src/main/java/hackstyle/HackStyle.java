package hackstyle;

import hackstyle.gui.InternetTab;
import hackstyle.gui.MainTab;
import hackstyle.keyboard.Keyboard;
import hackstyle.keyboard.KeyboardUtils;
import hackstyle.scripts.Script;
import hackstyle.scripts.exceptions.ScriptParsingException;
import hackstyle.scripts.parsing.HackStyleScriptParser;
import hackstyle.scripts.parsing.HackStyleSettings;
import hackstyle.scripts.parsing.HackStyleSettingsReader;
import hackstyle.scripts.parsing.ScriptVariableParser;
import hackstyle.scripts.variables.ConstantVariable;
import hackstyle.scripts.variables.ScrollBarVariable;
import hackstyle.scripts.variables.TextBarVariable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HackStyle extends Application {

    private static final String SETTINGS_FILE = "HackStyleSettings.xml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final HackStyleSettings settings = readSettings(SETTINGS_FILE);
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

        final ScriptVariableParser variableParser = new ScriptVariableParser();
        variableParser.addScriptVariableCreator("SLIDER", () ->
                new ScrollBarVariable(mainTab.getScrollBar()));
        variableParser.addScriptVariableCreator("TEXTBAR", () ->
                new TextBarVariable(mainTab.getTextField()));
        for (HackStyleSettings.Value value : settings.values) {
            variableParser.addScriptVariableCreator(value.name,
                    () -> new ConstantVariable(value.content));
        }

        final HackStyleScriptParser parser = new HackStyleScriptParser(variableParser);

        final List<Script> scripts = readScripts(settings, parser);

        scripts.forEach(mainTab::addScript);

        Keyboard.init();
        Keyboard.onKeyPress().perform(event -> {
            for (Script script : mainTab.getActiveScripts().get()) {
                if (KeyboardUtils.parseCharToKey(script.indicator()) == event.getKeyCode()) {
                    script.start();
                }
            }
        });
        Keyboard.onKeyRelease().perform(event -> {
            for (Script script : mainTab.getActiveScripts().get()) {
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
            System.exit(0);
        });
        primaryStage.show();

        if (!GlobalScreen.isNativeHookRegistered()) {
            System.exit(0);
        }
    }

    private static HackStyleSettings readSettings(String path) {
        try {
            return HackStyleSettingsReader.read(path);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            ErrorMessage.createErrorFile(e);
        }
        return null;
    }

    private static List<Script> readScripts(HackStyleSettings settings, HackStyleScriptParser parser) {
        try {
            final List<Script> scripts = new ArrayList<>(settings.scripts.size());
            for (HackStyleSettings.Script script : settings.scripts) {
                scripts.add(parser.parseScript(script));
            }
            return scripts;
        } catch (ScriptParsingException e) {
            ErrorMessage.createErrorFile(e);
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}