package hackstyle.scripts.actions;

import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.State;

public class LoopAction implements ScriptAction {

    private final int loops;
    private int index;

    public LoopAction(ScriptVariable loops) {
        this.loops = Integer.parseInt(loops.get());
    }

    @Override
    public State act(State state) {
        boolean found = false;
        if (this.loops == this.index) {
            final int thisIndex = state.getScriptActions().indexOf(this);
            for (int i = thisIndex + 1; !found && i < state.getScriptActions().size(); i--) {
                final ScriptAction scriptAction = state.getScriptActions().get(i);
                if (scriptAction instanceof EndLoopAction) {
                    state.setScriptIndex(i + 1);
                    found = true;
                }
            }
        }
        this.index++;
        return found ? state : state.next();
    }

    @Override
    public void reset() {
        this.index = 0;
    }
}
