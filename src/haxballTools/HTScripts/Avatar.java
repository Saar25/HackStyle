package haxballTools.HTScripts;

import haxballTools.HTGui;
import haxballTools.HTScript;

import static haxballTools.HTRobot.sendToChat;
import static haxballTools.HTRobot.sleep;

public final class Avatar implements HTScript {

    private final int indicator;
    private final Loader loader;

    private Avatar(Loader loader, int indicator) {
        this.indicator = indicator;
        this.loader = loader;
    }

    public static HTScript create(String avatar, int indicator) {
        return new Avatar(() -> avatar, indicator);
    }

    public static HTScript fromGUI(int indicator) {
        return new Avatar(HTGui::getTextFieldText, indicator);
    }

    @Override
    public void start(int d) {
        new Thread(() -> {
            int i = 0;
            String avatar = loader.getAvatar();
            while (i < avatar.length()) {
                sendToChat("/avatar " +
                        (avatar.charAt(i) == ' ' ? "" : avatar.charAt(i)) +
                        (i + 1 < avatar.length() ? avatar.charAt(i + 1) : ""));
                sleep(d * 3);
                i += 2;
            }
        }).start();
    }

    @Override
    public int getIndicator() {
        return indicator;
    }

    private interface Loader {
        String getAvatar();
    }
}
