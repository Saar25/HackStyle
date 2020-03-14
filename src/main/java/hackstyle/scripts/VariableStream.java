package hackstyle.scripts;

import java.util.Arrays;
import java.util.List;

public class VariableStream {

    private final List<ScriptVariable> variables;
    private int current;

    public VariableStream(List<ScriptVariable> variables) {
        this.variables = variables;
    }

    public VariableStream(ScriptVariable[] variables) {
        this.variables = Arrays.asList(variables);
    }

    public ScriptVariable next() {
        return variables.get(current++);
    }
}
