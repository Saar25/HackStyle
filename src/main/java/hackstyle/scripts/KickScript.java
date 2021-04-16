package hackstyle.scripts;

import hackstyle.HaxballRobot;

@Script("kick")
public class KickScript implements HackStyleScript {

    @ScriptParameter("name")
    private String name;

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
        return this.name;
    }

    @Override
    public char indicator() {
        return this.indicator.charAt(0);
    }

    @Override
    public void execute(ScriptInput input) {

    }

    @Override
    public void stop() {

    }

    public void run(ScriptInput input) {
        final int delay = getDelay(input);

        // super.startLoop(delay)
    }

    public void execute(int delay) {
        HaxballRobot.kick(delay);
        HaxballRobot.sleep(delay);
    }

}
