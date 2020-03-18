package hackstyle.scripts;

import java.util.Collections;
import java.util.List;

public class State {

    private final List<ScriptAction> scriptActions;

    private int scriptIndex = 0;
    private boolean running = true;

    public State(List<ScriptAction> scriptActions) {
        this.scriptActions = Collections.unmodifiableList(scriptActions);
    }

    public List<ScriptAction> getScriptActions() {
        return scriptActions;
    }

    public int getScriptIndex() {
        return scriptIndex;
    }

    public void nextScriptIndex() {
        this.scriptIndex++;
    }

    public void setScriptIndex(int scriptIndex) {
        this.scriptIndex = scriptIndex;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public State next() {
        nextScriptIndex();
        return this;
    }
}
