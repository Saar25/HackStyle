package hackstyle.scripts.actions;

import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.State;

public class SleepAction implements ScriptAction {

    private final ScriptVariable variable;

    public SleepAction(ScriptVariable variable) {
        this.variable = variable;
    }

    @Override
    public void act(State state) {
        try {
            Thread.sleep(Integer.parseInt(variable.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
