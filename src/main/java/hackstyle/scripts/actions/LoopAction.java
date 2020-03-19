package hackstyle.scripts.actions;

import hackstyle.scripts.*;

@ScriptActionSettings(keyword = "LOOP")
public class LoopAction implements ScriptAction {

    private final int loops;
    private int index;

    public LoopAction(VariableStream variables) {
        this.loops = Integer.parseInt(variables.next().get());
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
