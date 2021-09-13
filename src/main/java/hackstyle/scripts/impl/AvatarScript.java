package hackstyle.scripts.impl;

import hackstyle.HaxballRobot;
import hackstyle.scripts.HackStyleScript;
import hackstyle.scripts.Script;
import hackstyle.scripts.ScriptInput;
import hackstyle.scripts.ScriptParameter;

@Script(name = "avatar")
public class AvatarScript implements HackStyleScript {

    @ScriptParameter("title")
    private String title;

    @ScriptParameter("indicator")
    private String indicator;

    @ScriptParameter("delay")
    private String delay;

    @ScriptParameter("text")
    private String text;

    @ScriptParameter("DEFAULT-AVATAR")
    private String defaultAvatar;

    private boolean running = false;

    private int getDelay(ScriptInput input) {
        return this.delay == null || this.delay.isEmpty()
                ? input.getDelay() : Integer.parseInt(this.delay);
    }

    private String getText(ScriptInput input) {
        return this.text == null || this.text.isEmpty()
                ? input.getText() : this.text;
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
        int index = 0;

        while (this.running) {
            final String text = getText(input);
            final String avatar = getAvatar(text, index);
            HaxballRobot.setAvatar(avatar);
            HaxballRobot.sleep(delay);

            index = index + 2 < text.length() ? index + 2 : 0;
        }

        HaxballRobot.setAvatar(this.defaultAvatar);
    }

    @Override
    public void stop() {
        this.running = false;
    }

    private String getAvatar(String text, int index) {
        return text.length() > index + 1
                ? text.substring(index, index + 2)
                : text.charAt(index) + "";
    }
}
