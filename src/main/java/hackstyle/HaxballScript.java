package hackstyle;

import hackstyle.gui.HSGui;
import hackstyle.keyboard.Keyboard;
import hackstyle.scripts.Script;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class HaxballScript implements Script {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(50);

    private final int indicator;

    private boolean running = false;
    private boolean active = false;

    protected HaxballScript(int indicator) {
        this.indicator = indicator;
        this.registerToKeyboard();
    }

    private void registerToKeyboard() {
        Keyboard.onKeyPress(indicator()).perform(event -> {
            if (!isRunning() && active) {
                this.setRunning(true);
                EXECUTOR.submit(this::run);
            }
        });
        Keyboard.onKeyRelease(indicator()).perform(event -> {
            if (this.isRunning()) {
                this.setRunning(false);
                this.stop();
            }
        });
    }

    @Override
    public int indicator() {
        return indicator;
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

    protected void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        start();
    }

    @Override
    public String name() {
        return "null";
    }

    protected abstract void start();

    protected void stop() {

    }
}
