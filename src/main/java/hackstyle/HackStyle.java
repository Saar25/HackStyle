package hackstyle;

import hackstyle.gui.HSGui;
import hackstyle.keyboard.Keyboard;
import hackstyle.scripts.Script;
import hackstyle.scripts.actions.*;
import hackstyle.scripts.exceptions.ScriptParsingException;
import hackstyle.scripts.parsing.ScriptActionParser;
import hackstyle.scripts.parsing.ScriptVariableParser;
import hackstyle.scripts.parsing.ScriptsFileParser;
import hackstyle.scripts.variables.ConstantVariable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;

import java.io.IOException;
import java.util.List;

public class HackStyle extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ScriptParsingException {
        final ScriptActionParser actionParser = new ScriptActionParser();
        actionParser.addScriptActionCreator("SLEEP",
                variables -> new SleepAction(variables.next()));
        actionParser.addScriptActionCreator("KICK",
                variables -> new KickAction(variables.next()));
        actionParser.addScriptActionCreator("CLICK",
                variables -> new ClickAction(variables.next()));
        actionParser.addScriptActionCreator("WRITE",
                variables -> new WriteAction(variables.next()));
        actionParser.addScriptActionCreator("AVATAR",
                variables -> new AvatarAction(variables.next()));
        actionParser.addScriptActionCreator("AVATARS",
                variables -> new AvatarsAction(variables.next()));
        actionParser.addScriptActionCreator("LOOP",
                variables -> new LoopAction(variables.next()));
        actionParser.addScriptActionCreator("WHILE",
                variables -> new WhileAction(variables.next()));
        actionParser.addScriptActionCreator("ENDL",
                variables -> new EndLoopAction());

        final ScriptVariableParser variableParser = new ScriptVariableParser();
        variableParser.addScriptVariableCreator("$RUNNING", () -> new ConstantVariable("1"));
        variableParser.addScriptVariableCreator("SLIDER", () -> new ConstantVariable("FUCK YOU ALL"));

        final ScriptsFileParser scriptsFileParser = new ScriptsFileParser(actionParser, variableParser);
        final String code = new FileReader().readFIle("./HackStyleScripts.txt");
        final List<Script> scripts = scriptsFileParser.parse(code);

        final HSGui gui = new HSGui(scripts);
        gui.setTextFieldText("|><|");
        gui.setScrollBarValue(25);

        Keyboard.init();
        Keyboard.addListener(event -> {
            for (Script script : gui.getActiveScripts().get()) {
                if (script.)
            }
        })

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
}