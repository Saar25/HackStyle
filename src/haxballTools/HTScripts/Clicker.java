package haxballTools.HTScripts;

import haxballTools.HTScript;

import static haxballTools.HTRobot.click;
import static haxballTools.HTRobot.sleep;

public final class Clicker implements HTScript {

    private final int indicator;
    private boolean running;

    private Clicker(int indicator) {
        this.indicator = indicator;
        this.running = false;
    }

    public static HTScript create(int indicator) {
        return new Clicker(indicator);
    }

    @Override
    public void start(int d) {
        running = true;
        new Thread(() -> {
            while (running) {
                click(d);
                sleep(d);
            }
        }).start();
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public int getIndicator() {
        return indicator;
    }
}
