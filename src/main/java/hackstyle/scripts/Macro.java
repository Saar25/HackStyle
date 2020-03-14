package hackstyle.scripts;

import hackstyle.HaxballRobot;
import hackstyle.HaxballScript;

public final class Macro extends HaxballScript {

    private final int kicks;

    private Macro(int kicks, int indicator) {
        super(indicator);
        this.kicks = kicks;
    }

    public static Macro endless(int indicator) {
        return new Macro(-1, indicator);
    }

    public static Macro create(int kicks, int indicator) {
        return new Macro(kicks, indicator);
    }

    @Override
    public void start() {
        int times = kicks;
        while (isRunning() && times != 0) {
            HaxballRobot.kick((int) getDuration());
            HaxballRobot.sleep((int) getDuration());
            times--;
        }
    }
}
