package hackstyle.scripts;

import java.util.List;

public class ExternalScript implements HackStyleScript {

    private final String name;
    private final char indicator;
    private final List<ScriptAction> scriptActions;

    private boolean running = false;

    public ExternalScript(String name, char indicator, List<ScriptAction> scriptActions) {
        this.name = name;
        this.indicator = indicator;
        this.scriptActions = scriptActions;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public char indicator() {
        return indicator;
    }

    @Override
    public void execute(ScriptInput input) {
        this.running = true;

        State state = new State(this.scriptActions);

        while (state.getScriptIndex() < this.scriptActions.size() && this.running) {
            final ScriptAction action = this.scriptActions.get(state.getScriptIndex());
            state = action.act(state);

            state.setRunning(this.running);
        }
    }

    @Override
    public void stop() {
        this.running = false;
        this.scriptActions.forEach(ScriptAction::reset);
    }
}
