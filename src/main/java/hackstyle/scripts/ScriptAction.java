package hackstyle.scripts;

public interface ScriptAction {

    State act(State state);

    default void reset() {

    }

    interface Creator {
        ScriptAction create(VariableStream variables);
    }
}
