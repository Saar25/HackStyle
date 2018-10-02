package haxballTools.HTScripts;

import haxballTools.HTScript;

import static haxballTools.HTRobot.kick;
import static haxballTools.HTRobot.sleep;

public final class Macro implements HTScript{

    private final int indicator;
    private final int kicks;
    private boolean running;

    private Macro(int kicks, int indicator) {
        this.indicator = indicator;
        this.kicks = kicks;
        this.running = false;
    }

    public static Macro createEndless(int indicator) {
        return new Macro(-1, indicator);
    }

    public static Macro create(int kicks, int indicator) {
        return new Macro(kicks, indicator);
    }

    @Override
    public void start(int duration) {
        this.running = true;
        new Thread(() -> {
            int times = kicks;
            while (running && times != 0) {
                kick(duration);
                sleep(duration);
                times--;
            }
        }).start();
    }

    @Override
    public void stop() {
        this.running = false;
    }

    @Override
    public int getIndicator() {
        return indicator;
    }
}
