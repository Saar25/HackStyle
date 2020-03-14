package hackstyle.scripts;

public class ScriptCommand {

    private final String action;
    private final VariableStream variables;

    public ScriptCommand(String action, VariableStream variables) {
        this.action = action;
        this.variables = variables;
    }

    public String getAction() {
        return action;
    }

    public VariableStream getVariables() {
        return variables;
    }
}
