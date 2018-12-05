package hackstyle.scripts;

import hackstyle.HSGui;
import hackstyle.HaxballRobot;
import hackstyle.HaxballScript;

public final class Avatar extends HaxballScript {

    private final Loader loader;

    private Avatar(Loader loader, int indicator) {
        super(indicator);
        this.loader = loader;
    }

    public static Avatar create(String avatar, int indicator) {
        return new Avatar(() -> avatar, indicator);
    }

    public static Avatar fromGUI(int indicator) {
        return new Avatar(HSGui::getTextFieldText, indicator);
    }

    @Override
    public void start() {
        int i = 0;
        String avatar = loader.getAvatar();
        while (isRunning()) {
            HaxballRobot.setAvatar(getAvatar(avatar, i));
            i = i + 2 < avatar.length() ? i + 2 : 0;
            HaxballRobot.sleep((int) (getDuration() * 3));
        }
        HaxballRobot.setAvatar(getAvatar(avatar, 0));
    }

    private String getAvatar(String avatar, int i) {
        return avatar.length() > i + 1
                ? avatar.substring(i, i + 2)
                : avatar.charAt(i) + "";
    }

    private interface Loader {
        String getAvatar();
    }
}
