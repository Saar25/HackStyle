package hackstyle.scripts;

import hackstyle.HSGui;
import hackstyle.HaxballRobot;
import hackstyle.HaxballScript;

public final class Spam extends HaxballScript {

    private final Loader loader;

    public Spam(Loader loader, int indicator) {
        super(indicator);
        this.loader = loader;
    }

    public static Spam create(String[] s, int indicator) {
        return new Spam(() -> s, indicator);
    }

    public static Spam fromGUI(String regex, int indicator) {
        return new Spam(() -> HSGui.getTextFieldText().split(regex), indicator);
    }

    @Override
    public void start() {
        String[] strings = loader.getStrings();
        for (String string : strings) {
            HaxballRobot.sendToChat(string);
            HaxballRobot.sleep((int) getDuration());
        }
    }

    private interface Loader {
        String[] getStrings();
    }

}
