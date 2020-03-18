package hackstyle.scripts.actions;

import hackstyle.HaxballRobot;
import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.State;

public class AvatarAction implements ScriptAction {

    private final ScriptVariable avatar;

    public AvatarAction(ScriptVariable avatar) {
        this.avatar = avatar;
    }

    @Override
    public void act(State state) {
        HaxballRobot.setAvatar(avatar.get());
    }
}
