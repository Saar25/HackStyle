package hackstyle.scripts.actions;

import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.State;

public class LoopAction implements ScriptAction {

    private final int loops;

    public LoopAction(ScriptVariable loops) {
        this.loops = Integer.parseInt(loops.get());
    }

    @Override
    public void act(State state) {
        if (loops == 0) {
            final int thisIndex = state.getScriptActions().indexOf(this);
            boolean found = false;
            for (int i = thisIndex - 1; !found && i >= 0; i--) {
                final ScriptAction scriptAction = state.getScriptActions().get(i);
                if (scriptAction instanceof LoopAction) {
                    state.setScriptIndex(i);
                    found = true;
                }
            }
        }
    }

}
