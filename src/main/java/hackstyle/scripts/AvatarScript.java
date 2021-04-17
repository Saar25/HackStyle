package hackstyle.scripts;

import hackstyle.HaxballRobot;

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

    @ScriptParameter("default-avatar")
    private String defaultAvatar;

    private boolean running = false;

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
        this.running = true;

        final int delay = getDelay(input);
        int index = 0;

        while (this.running) {
            final String avatar = getAvatar(index);
            HaxballRobot.setAvatar(avatar);
            HaxballRobot.sleep(delay);

            index = index + 2 < avatar.length() ? index + 2 : 0;
        }

        HaxballRobot.setAvatar(this.defaultAvatar);
    }

    @Override
    public void stop() {
        this.running = false;
    }

    private String getAvatar(int index) {
        return this.text.length() > index + 1
                ? this.text.substring(index, index + 2)
                : this.text.charAt(index) + "";
    }
}
