package hackstyle;

import hackstyle.gui.InternetTab;
import hackstyle.gui.MainTab;
import hackstyle.keyboard.Keyboard;
import hackstyle.keyboard.KeyboardUtils;
import hackstyle.scripts.Script;
import hackstyle.scripts.exceptions.ScriptParsingException;
import hackstyle.scripts.parsing.ScriptVariableParser;
import hackstyle.scripts.parsing.ScriptsFileParser;
import hackstyle.scripts.variables.ConstantVariable;
import hackstyle.scripts.variables.ScrollBarVariable;
import hackstyle.scripts.variables.TextBarVariable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class HackStyle extends Application {

    private static final String SCRIPT_FILE = "./HackStyleScripts.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final TabPane gui = new TabPane();
        final MainTab mainTab = new MainTab();
        mainTab.getTextField().setText("|><|");
        mainTab.getScrollBar().setValue(25);

        gui.getTabs().add(mainTab);
        gui.getTabs().add(new InternetTab());

        final ScriptVariableParser variableParser = new ScriptVariableParser();
        variableParser.addScriptVariableCreator("SLIDER", () -> new ScrollBarVariable(mainTab.getScrollBar()));
        variableParser.addScriptVariableCreator("TEXTBAR", () -> new TextBarVariable(mainTab.getTextField()));
        variableParser.addScriptVariableCreator("DEFAULT_AVATAR", () -> new ConstantVariable("|>"));
        final List<Script> scripts = readScripts(variableParser);

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
            for (Script script : scripts) {
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

    private static List<Script> readScripts(ScriptVariableParser variableParser) {
        try {
            final ScriptsFileParser scriptsFileParser = new ScriptsFileParser(variableParser);
            final String code = new FileReader().readFIle(SCRIPT_FILE);
            return scriptsFileParser.parse(code);
        } catch (ScriptParsingException | IOException | NoSuchMethodException e) {
            ErrorMessage.createErrorFile(e);
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}