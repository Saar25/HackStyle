package hackstyle.scripts.actions;

import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.ScriptVariable;
import hackstyle.scripts.State;

public class WhileAction implements ScriptAction {

    private final ScriptVariable variable;

    public WhileAction(ScriptVariable variable) {
        this.variable = variable;
    }

    @Override
    public void act(State state) {
        if (variable.get()) {
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
