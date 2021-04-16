package hackstyle.scripts;

public class ScriptInput {

    private final int delay;
    private final String text;

    public ScriptInput(int delay, String text) {
        this.delay = delay;
        this.text = text;
    }

    public int getDelay() {
        return this.delay;
    }

    public String getText() {
        return this.text;
    }
}
