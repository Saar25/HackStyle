package hackstyle.scripts;

import java.lang.reflect.InvocationTargetException;

public interface ScriptAction {

    State act(State state);

    default void reset() {

    }

    interface Creator {
        ScriptAction create(VariableStream variables) throws IllegalAccessException, InvocationTargetException, InstantiationException;
    }
}
