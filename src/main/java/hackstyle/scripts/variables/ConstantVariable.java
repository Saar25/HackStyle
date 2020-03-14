package hackstyle.scripts.variables;

import hackstyle.scripts.ScriptVariable;

public class ConstantVariable implements ScriptVariable {

    private final Object value;

    public ConstantVariable(Object value) {
        this.value = value;
    }

    @Override
    public Object get() {
        return value;
    }
}
