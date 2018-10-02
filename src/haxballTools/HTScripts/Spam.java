package haxballTools.HTScripts;

import haxballTools.HTGui;
import haxballTools.HTScript;

import static haxballTools.HTRobot.sendToChat;
import static haxballTools.HTRobot.sleep;

public final class Spam implements HTScript {

    private final int indicator;
    private final Loader loader;

    public Spam(Loader loader, int indicator) {
        this.indicator = indicator;
        this.loader = loader;
    }

    public static HTScript create(String[] s, int indicator) {
        return new Spam(() -> s, indicator);
    }

    public static HTScript fromGUI(String regex, int indicator) {
        return new Spam(() -> HTGui.getTextFieldText().split(regex), indicator);
    }

    @Override
    public void start(int d) {
        new Thread(() -> {
            String[] strings = loader.getStrings();
            for (String string : strings) {
                sendToChat(string);
                sleep(d);
            }
        }).start();
    }

    @Override
    public int getIndicator() {
        return indicator;
    }

    private interface Loader {
        String[] getStrings();
    }

}
