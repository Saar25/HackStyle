package hackstyle.scripts.actions;

import hackstyle.scripts.*;

@ScriptActionSettings(keyword = "SLEEP")
public class SleepAction implements ScriptAction {

    private final ScriptVariable variable;

    public SleepAction(VariableStream variables) {
        this.variable = variables.next();
    }

    @Override
    public State act(State state) {
        try {
            Thread.sleep(Integer.parseInt(variable.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return state.next();
    }
}
