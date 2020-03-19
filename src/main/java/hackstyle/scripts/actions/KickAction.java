package hackstyle.scripts.actions;

import hackstyle.HaxballRobot;
import hackstyle.scripts.*;

@ScriptActionSettings(keyword = "KICK")
public class KickAction implements ScriptAction {

    private final ScriptVariable length;

    public KickAction(VariableStream variables) {
        this.length = variables.next();
    }

    @Override
    public State act(State state) {
        HaxballRobot.kick(Integer.parseInt(length.get()));
        return state.next();
    }
}
