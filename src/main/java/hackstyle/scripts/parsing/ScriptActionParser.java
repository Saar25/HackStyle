package hackstyle.scripts.parsing;

import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.VariableStream;
import hackstyle.scripts.exceptions.InvalidScriptActionException;

import java.util.HashMap;
import java.util.Map;

public class ScriptActionParser {

    private final Map<String, ScriptAction.Creator> scriptActionCreators = new HashMap<>();

    public void addScriptActionCreator(String variableName, ScriptAction.Creator creator) {
        this.scriptActionCreators.put(variableName, creator);
    }

    public ScriptAction parse(String actionName, VariableStream variables) throws InvalidScriptActionException {
        final ScriptAction.Creator creator = scriptActionCreators.get(actionName);
        if (creator != null) {
            return creator.create(variables);
        }
        throw new InvalidScriptActionException("Cannot parse action: " + actionName);
    }

}
