package hackstyle.scripts;

import hackstyle.HaxballRobot;

@Script(name = "double-kick")
public class DoubleKickScript implements HackStyleScript {

    @ScriptParameter("title")
    private String title;

    @ScriptParameter("indicator")
    private String indicator;

    @ScriptParameter("delay")
    private String delay;

    private int getDelay(ScriptInput input) {
        return this.delay.isEmpty()
                ? input.getDelay()
                : Integer.parseInt(this.delay);
    }

    @Override
    public String name() {
        return this.title;
    }

    @Override
    public char indicator() {
        return this.indicator.charAt(0);
    }

    @Override
    public void execute(ScriptInput input) {
        final int delay = getDelay(input);
        HaxballRobot.kick(delay);
        HaxballRobot.sleep(delay);
        HaxballRobot.kick(delay);
        HaxballRobot.sleep(delay);
    }

    @Override
    public void stop() {
    }
}
