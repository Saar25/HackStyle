package haxballTools.HTScripts;

import haxballTools.HTScript;

import static haxballTools.HTRobot.click;
import static haxballTools.HTRobot.sleep;

public class Clicker implements HTScript {

    private boolean running;
    private int duration;

    public static HTScript create() {
        return new Clicker();
    }

    @Override
    public void start(int d) {
        running = true;
        duration = d;

        new Thread(() -> {
            while (running) {
                click(duration);
                sleep(duration);
            }
        }).start();
    }

    @Override
    public void stop() {
        running = false;
    }
}
