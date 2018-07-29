package haxballTools.HTScripts;

import haxballTools.HTScript;

import static haxballTools.HTRobot.click;
import static haxballTools.HTRobot.sleep;

public final class Clicker {

    private Clicker() {

    }

    public static HTScript create() {
        return new HTScript() {

            private boolean running;
            private int duration;

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
        };
    }
}
