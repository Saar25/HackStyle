package hackstyle.scripts;

public interface ScriptAction {

    State act(State state);

    default void reset() {

    }

}
