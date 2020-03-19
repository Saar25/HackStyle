package hackstyle.scripts.parsing;

import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.variables.ConstantVariable;

import java.util.HashMap;
import java.util.Map;

public class ScriptVariableParser {

    private final Map<String, ScriptVariable.Creator> scriptVariableCreators = new HashMap<>();

    public void addScriptVariableCreator(String variableName, ScriptVariable.Creator creator) {
        this.scriptVariableCreators.put(variableName, creator);
    }

    public ScriptVariable parse(String variableName) {
        final ScriptVariable.Creator creator = scriptVariableCreators.get(variableName);
        return creator == null ? new ConstantVariable(variableName) : creator.create();
    }

}
