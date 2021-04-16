package hackstyle.scripts;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public abstract class HackStyleScriptRunner {

    private final DoubleProperty scrollBarValue;
    private final StringProperty textFieldValue;

    private boolean running = false;

    public HackStyleScriptRunner(DoubleProperty scrollBarValue, StringProperty textFieldValue) {
        this.scrollBarValue = scrollBarValue;
        this.textFieldValue = textFieldValue;
    }

    public void start() {
        final ScriptInput input = new ScriptInput(
                (int) (1000 / this.scrollBarValue.get()),
                this.textFieldValue.get());

    }

    public void stop() {
        this.running = false;
    }

    private void startLoop(int delay) {
        this.running = true;

        while (this.running) {
            execute(delay);
        }
    }

    public abstract void execute(int delay);
}
