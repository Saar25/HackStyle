package hackstyle.scripts;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HackStyleScriptRunner {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private final DoubleProperty scrollBarValue;
    private final StringProperty textFieldValue;

    public HackStyleScriptRunner(DoubleProperty scrollBarValue, StringProperty textFieldValue) {
        this.scrollBarValue = scrollBarValue;
        this.textFieldValue = textFieldValue;
    }

    private ScriptInput createScriptInput() {
        final int delay = (int) (1000 / this.scrollBarValue.get());
        final String text = this.textFieldValue.get();
        return new ScriptInput(delay, text);
    }

    public void run(HackStyleScript script) {
        final ScriptInput input = createScriptInput();

        this.executorService.submit(() -> {
            try {
                script.execute(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void dispose() {
        this.executorService.shutdown();
    }
}
