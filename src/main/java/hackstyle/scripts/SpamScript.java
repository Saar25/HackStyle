package hackstyle.scripts;

import hackstyle.HaxballRobot;

@Script(name = "spam")
public class SpamScript implements HackStyleScript {

    @ScriptParameter("title")
    private String title;

    @ScriptParameter("indicator")
    private String indicator;

    @ScriptParameter("delay")
    private String delay;

    @ScriptParameter("text")
    private String text;

    @ScriptParameter("MAX-SPAM-REPEAT")
    private String maxSpamRepeat;

    private boolean running = false;

    private int getDelay(ScriptInput input) {
        return this.delay == null || this.delay.isEmpty()
                ? input.getDelay() : Integer.parseInt(this.delay);
    }

    private String getText(ScriptInput input) {
        return this.text == null || this.text.isEmpty()
                ? input.getText() : this.text;
    }

    private int getMaxSpamRepeat() {
        return this.maxSpamRepeat == null || this.maxSpamRepeat.isEmpty()
                ? Integer.MAX_VALUE : Integer.parseInt(this.maxSpamRepeat);
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

        final int maxSpamRepeat = getMaxSpamRepeat();

        for (int i = 0; i < maxSpamRepeat && this.running; i++) {
            final String text = getText(input);
            HaxballRobot.sendToChat(text);
            HaxballRobot.sleep(delay);
        }
    }

    @Override
    public void stop() {
        this.running = false;
    }
}
