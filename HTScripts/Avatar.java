package haxballTools.HTScripts;

import haxballTools.HTGui;
import haxballTools.HTScript;

import static haxballTools.HTRobot.sendToChat;
import static haxballTools.HTRobot.sleep;

public class Avatar {

    private Avatar() {

    }

    public static HTScript create(String a) {
        return new HTScript() {
            private String avatar;
            private int duration;

            @Override
            public void start(int d) {
                this.duration = d;
                this.avatar = a;

                new Thread(() -> {
                    int i = 0;
                    while (i < avatar.length()) {
                        sendToChat("/avatar " +
                                (avatar.charAt(i) == ' ' ? "" : avatar.charAt(i)) +
                                (i + 1 < avatar.length() ? avatar.charAt(i + 1) : ""));
                        sleep(duration * 3);
                        i += 2;
                    }
                }).start();
            }
        };
    }

    public static HTScript fromGUI() {
        return e -> {
            int duration = (int) HTGui.getScrollBarValue();
            String avatar = HTGui.getTextFieldText();
            new Thread(() -> {
                int i = 0;
                while (i < avatar.length()) {
                    sendToChat("/avatar " +
                            (avatar.charAt(i) == ' ' ? "" : avatar.charAt(i)) +
                            (i + 1 < avatar.length() ? avatar.charAt(i + 1) : ""));
                    sleep(duration * 3);
                    i += 2;
                }
            }).start();
        };
    }

}
