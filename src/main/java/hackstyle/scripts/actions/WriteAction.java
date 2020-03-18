package hackstyle.scripts.actions;

import hackstyle.HaxballRobot;
import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.State;

public class WriteAction implements ScriptAction {

    private final ScriptVariable message;

    public WriteAction(ScriptVariable message) {
        this.message = message;
    }

    @Override
    public State act(State state) {
        HaxballRobot.sendToChat(message.get());
        return state.next();
    }
}
