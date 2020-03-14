package hackstyle.scripts;

import hackstyle.HaxballRobot;
import hackstyle.HaxballScript;

public final class Clicker extends HaxballScript {

    private Clicker(int indicator) {
        super(indicator);
    }

    public static Clicker create(int indicator) {
        return new Clicker(indicator);
    }

    @Override
    public void start() {
        while (isRunning()) {
            HaxballRobot.click((int) getDuration());
            HaxballRobot.sleep((int) getDuration());
        }
    }

}
