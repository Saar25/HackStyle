package hackstyle;

import hackstyle.gui.HSGui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class HaxballScript {

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    private boolean running = false;
    private boolean active = false;

    protected HaxballScript(int indicator) {
        Keyboard.addListener((key, isDown) -> {
            if (!active || key != indicator) {
                return;
            }
            if (!running && isDown) {
                running = true;
                EXECUTOR.submit(this::start);
            } else if (running) {
                running = false;
                stop();
            }
        });
    }

    protected HaxballScript(char indicator) {
        this(KeyboardUtils.parseCharToKey(indicator));
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    protected double getDuration() {
        return 1000 / HSGui.getScrollBarValue();
    }

    protected String getText() {
        return HSGui.getTextFieldText();
    }

    protected boolean isRunning() {
        return running;
    }

    protected abstract void start();

    protected void stop() {

    }
}
