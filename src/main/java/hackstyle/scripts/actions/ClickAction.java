package hackstyle.scripts.actions;

import hackstyle.HaxballRobot;
import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.State;

public class ClickAction implements ScriptAction {

    private final ScriptVariable length;

    public ClickAction(ScriptVariable length) {
        this.length = length;
    }

    @Override
    public State act(State state) {
        HaxballRobot.click(Integer.parseInt(length.get()));
        return state.next();
    }
}
