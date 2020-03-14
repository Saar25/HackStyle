package hackstyle.scripts.parsing;

import hackstyle.ErrorMessage;
import hackstyle.FileReader;
import hackstyle.scripts.*;
import hackstyle.scripts.actions.*;
import hackstyle.scripts.exceptions.InvalidScriptActionException;
import hackstyle.scripts.exceptions.ScriptParsingException;
import hackstyle.scripts.variables.ConstantVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScriptsFileParser {

    private final ScriptActionParser scriptActionParser;
    private final ScriptVariableParser scriptVariableParser;

    public ScriptsFileParser(ScriptActionParser scriptActionParser, ScriptVariableParser scriptVariableParser) {
        this.scriptActionParser = scriptActionParser;
        this.scriptVariableParser = scriptVariableParser;
    }

    public static void main(String[] args) throws IOException, ScriptParsingException {
        final ScriptActionParser actionParser = new ScriptActionParser();
        actionParser.addScriptActionCreator("SLEEP",
                variables -> new SleepAction(variables.next()));
        actionParser.addScriptActionCreator("KICK",
                variables -> new KickAction(variables.next()));
        actionParser.addScriptActionCreator("CLICK",
                variables -> new ClickAction(variables.next()));
        actionParser.addScriptActionCreator("LOOP",
                variables -> new LoopAction(variables.next()));
        actionParser.addScriptActionCreator("ENDL",
                variables -> new EndLoopAction());

        final ScriptVariableParser variableParser = new ScriptVariableParser();
        variableParser.addScriptVariableCreator("SLIDER", () -> new ConstantVariable("FUCK YOU ALL"));

        final ScriptsFileParser scriptsFileParser = new ScriptsFileParser(actionParser, variableParser);

        final String code = new FileReader().readFIle("./HackStyleScripts.txt");

        for (Script script : scriptsFileParser.parse(code)) {
            System.out.println(script);
        }
    }

    public List<Script> parse(String code) throws ScriptParsingException {
        final List<Script> scripts = new ArrayList<>();
        for (String scriptCode : code.split(",")) {
            scripts.add(parseScript(scriptCode));
        }
        return scripts;
    }

    private Script parseScript(String scriptCode) throws ScriptParsingException {
        final String[] split = scriptCode.split(" ", 2);
        final String[] settings = split[0].split(":");

        final String name = settings[0];

        final String indicator = settings[1];
        Validator.validateIndicator(indicator);

        final String code = split[1];
        Validator.validateCode(code);

        final List<ScriptAction> actions = parseCode(code);
        return new ScriptImpl(name, indicator.charAt(0), actions);
    }

    private List<ScriptAction> parseCode(String code) throws InvalidScriptActionException {
        final List<ScriptAction> commands = new ArrayList<>();
        code = code.replace("{", "").replace("}", "").replace("\r", "").replace("\t", "").substring(1);
        for (String command : code.split("\n")) {
            final String[] split = command.split(" ", 2);
            final String[] variables = split.length > 1 ? split[1].split(" ") : new String[0];

            final VariableStream variableStream = parseVariables(variables);
            final ScriptAction action = scriptActionParser.parse(split[0], variableStream);

            commands.add(action);
        }
        return commands;
    }

    private VariableStream parseVariables(String[] variables) {
        return new VariableStream(Arrays.stream(variables)
                .map(scriptVariableParser::parse)
                .collect(Collectors.toList()));
    }
}
