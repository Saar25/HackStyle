package haxballTools.HTScripts;

import haxballTools.HTScript;

import static haxballTools.HTRobot.kick;
import static haxballTools.HTRobot.sleep;

public class Macro implements HTScript {

    private boolean running;
    private int duration;

    public static HTScript create() {
        return new Macro();
    }

    @Override
    public void start(int d) {
        this.running = true;
        this.duration = d;

        new Thread(() -> {
            while (running) {
                kick(duration);
                sleep(duration);
            }
        }).start();
    }

    @Override
    public void stop() {
        running = false;
    }
}
