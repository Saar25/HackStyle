package hackstyle.scripts.impl;

import hackstyle.HaxballRobot;
import hackstyle.scripts.HackStyleScript;
import hackstyle.scripts.Script;
import hackstyle.scripts.ScriptInput;
import hackstyle.scripts.ScriptParameter;

@Script(name = "kick")
public class KickScript implements HackStyleScript {

    @ScriptParameter("title")
    private String title;

    @ScriptParameter("indicator")
    private String indicator;

    @ScriptParameter("delay")
    private String delay;

    private boolean running = false;

    private int getDelay(ScriptInput input) {
        return this.delay == null || this.delay.isEmpty()
                ? input.getDelay() : Integer.parseInt(this.delay);
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
        this.running = true;

        final int delay = getDelay(input);
        while (this.running) {
            HaxballRobot.kick(delay);
            HaxballRobot.sleep(delay);
        }
    }

    @Override
    public void stop() {
        this.running = false;
    }
}
