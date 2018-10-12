package haxballTools.HTScripts;

import haxballTools.HTGui;
import haxballTools.HTRobot;
import haxballTools.HTScript;

public final class Avatar implements HTScript {

    private final int indicator;
    private final Loader loader;

    private boolean running = false;

    private Avatar(Loader loader, int indicator) {
        this.indicator = indicator;
        this.loader = loader;
    }

    public static Avatar create(String avatar, int indicator) {
        return new Avatar(() -> avatar, indicator);
    }

    public static Avatar fromGUI(int indicator) {
        return new Avatar(HTGui::getTextFieldText, indicator);
    }

    @Override
    public void start(int duration) {
        running = true;
        new Thread(() -> {
            int i = 0;
            String avatar = loader.getAvatar();
            while (running) {
                HTRobot.setAvatar(getAvatar(avatar, i));
                i = i + 2 < avatar.length() ? i + 2 : 0;
                HTRobot.sleep(duration * 3);
            }
            HTRobot.setAvatar(getAvatar(avatar, 0));
        }).start();
    }

    private String getAvatar(String avatar, int i) {
        return avatar.length() > i + 1
                ? avatar.substring(i, i + 2)
                : avatar.charAt(i) + "";
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public int getIndicator() {
        return indicator;
    }

    private interface Loader {
        String getAvatar();
    }
}
