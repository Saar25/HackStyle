package hackstyle.scripts.actions;

import hackstyle.HaxballRobot;
import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.State;

public class AvatarsAction implements ScriptAction {

    private final ScriptVariable avatar;
    private int index = 0;

    public AvatarsAction(ScriptVariable avatar) {
        this.avatar = avatar;
    }

    @Override
    public State act(State state) {
        HaxballRobot.setAvatar(getAvatar());
        index = index + 2 < avatar.get().length() ? index + 2 : 0;
        return state.next();
    }

    private String getAvatar() {
        return avatar.get().length() > index + 1
                ? avatar.get().substring(index, index + 2)
                : avatar.get().charAt(index) + "";
    }

    @Override
    public void reset() {
        this.index = 0;
        HaxballRobot.setAvatar(getAvatar());
    }
}