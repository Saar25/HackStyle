package hackstyle.scripts;

import hackstyle.HaxballRobot;

@Script(name = "click")
public class ClickScript implements HackStyleScript {

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
            HaxballRobot.click(delay);
            HaxballRobot.sleep(delay);
        }
    }

    @Override
    public void stop() {
        this.running = false;
    }
}
