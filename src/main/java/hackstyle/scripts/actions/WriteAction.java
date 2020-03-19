package hackstyle.scripts.actions;

import hackstyle.HaxballRobot;
import hackstyle.scripts.*;

@ScriptActionSettings(keyword = "WRITE")
public class WriteAction implements ScriptAction {

    private final ScriptVariable message;

    public WriteAction(VariableStream variables) {
        this.message = variables.next();
    }

    @Override
    public State act(State state) {
        HaxballRobot.sendToChat(message.get());
        return state.next();
    }
}
