package hackstyle.scripts.actions;

import hackstyle.HaxballRobot;
import hackstyle.scripts.*;

@ScriptActionSettings(keyword = "AVATAR")
public class AvatarAction implements ScriptAction {

    private final ScriptVariable avatar;

    public AvatarAction(VariableStream variables) {
        this.avatar = variables.next();
    }

    @Override
    public State act(State state) {
        HaxballRobot.setAvatar(avatar.get());
        return state.next();
    }
}
