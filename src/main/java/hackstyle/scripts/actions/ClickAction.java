package hackstyle.scripts.actions;

import hackstyle.HaxballRobot;
import hackstyle.scripts.*;

@ScriptActionSettings(keyword = "CLICK")
public class ClickAction implements ScriptAction {

    private final ScriptVariable length;

    public ClickAction(VariableStream variables) {
        this.length = variables.next();
    }

    @Override
    public State act(State state) {
        HaxballRobot.click(Integer.parseInt(length.get()));
        return state.next();
    }
}
