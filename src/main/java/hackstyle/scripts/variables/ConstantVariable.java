package hackstyle.scripts.variables;

import hackstyle.scripts.ScriptVariable;

public class ConstantVariable implements ScriptVariable {

    private final String value;

    public ConstantVariable(String value) {
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }
}
