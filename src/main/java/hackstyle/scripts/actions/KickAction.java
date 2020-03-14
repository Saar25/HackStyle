package hackstyle.scripts.actions;

import hackstyle.HaxballRobot;
import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.State;

public class KickAction implements ScriptAction {

    private final ScriptVariable length;

    public KickAction(ScriptVariable length) {
        this.length = length;
    }

    @Override
    public void act(State state) {
        HaxballRobot.kick(Integer.parseInt(length.get()));
    }
}
