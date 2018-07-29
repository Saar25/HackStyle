package haxballTools.HTScripts;

import haxballTools.HTScript;

import static haxballTools.HTRobot.kick;
import static haxballTools.HTRobot.sleep;

public final class Macro {

    private Macro() {

    }

    public static HTScript create() {
        return create(-1);
    }

    public static HTScript create(int kicks) {
        return new HTScript() {
            private boolean running;

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
                running = false;
            }
        };
    }
}
