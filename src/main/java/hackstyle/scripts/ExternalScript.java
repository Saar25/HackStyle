package hackstyle.scripts;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScriptImpl implements Script {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final String name;
    private final char indicator;
    private final List<ScriptAction> scriptActions;

    private boolean running = false;

    public ScriptImpl(String name, char indicator, List<ScriptAction> scriptActions) {
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
    public void start() {
        if (!this.running) {
            this.running = true;

            executor.submit(() -> {
                State state = new State(scriptActions);

                while (state.getScriptIndex() < scriptActions.size() && running) {
                    final ScriptAction action = scriptActions.get(state.getScriptIndex());
                    state = action.act(state);
                    state.setRunning(running);
                }
            });
        }
    }

    @Override
    public void stop() {
        this.running = false;
        this.scriptActions.forEach(ScriptAction::reset);
    }
}
