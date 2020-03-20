package hackstyle.scripts.parsing;

import hackstyle.scripts.ExternalScript;
import hackstyle.scripts.Script;
import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.VariableStream;
import hackstyle.scripts.exceptions.InvalidScriptActionException;
import hackstyle.scripts.exceptions.ScriptParsingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HackStyleScriptParser {

    private final ScriptActionParser scriptActionParser;
    private final ScriptVariableParser scriptVariableParser;

    public HackStyleScriptParser(ScriptVariableParser scriptVariableParser) {
        this.scriptActionParser = new ScriptActionParser();
        this.scriptVariableParser = scriptVariableParser;
    }

    public Script parseScript(HackStyleSettings.Script script) throws ScriptParsingException {
        final List<ScriptAction> actions = parseCode(script.code);
        return new ExternalScript(script.name, script.indicator.charAt(0), actions);
    }

    private List<ScriptAction> parseCode(String code) throws InvalidScriptActionException {
        final List<ScriptAction> commands = new ArrayList<>();
        code = code.replaceAll("\r", "").replaceAll("\t", "").trim().replaceAll("\n\\s+", "\n");
        for (String command : code.split("\n")) {
            final String[] split = command.split(" ", 2);
            final String[] variables = split.length > 1 ? split[1].split("\\s+") : new String[0];

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
