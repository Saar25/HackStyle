package hackstyle.scripts;

import java.util.Collections;
import java.util.List;

public class State {

    private final List<ScriptAction> scriptActions;

    private int scriptIndex = 0;

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
}
