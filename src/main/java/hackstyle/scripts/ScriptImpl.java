package hackstyle.scripts;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScriptImpl implements Script {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final String name;
    private final int indicator;
    private final List<ScriptAction> scriptActions;

    public ScriptImpl(String name, int indicator, List<ScriptAction> scriptActions) {
        this.name = name;
        this.indicator = indicator;
        this.scriptActions = scriptActions;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int indicator() {
        return indicator;
    }

    @Override
    public void run() {
        final State state = new State(scriptActions);

        executor.submit(() -> {
            for (ScriptAction scriptAction : scriptActions) {
                scriptAction.act(state);
                state.nextScriptIndex();
            }
        });
    }
}
