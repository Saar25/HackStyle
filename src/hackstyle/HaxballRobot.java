package hackstyle;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public final class HaxballRobot {

    private static final Robot ROBOT = createRobot();
    private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();

    private HaxballRobot() {

    }

    private static Robot createRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            System.err.println("Unable to create robot");
            System.exit(-1);
        }
        return null;
    }

    public static void sendToChat(String string) {
        StringSelection stringSelection = new StringSelection(string);
        CLIPBOARD.setContents(stringSelection, stringSelection);

        type(KeyEvent.VK_TAB);
        type(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        type(KeyEvent.VK_ENTER);
    }

    public static void setAvatar(String avatar) {
        sendToChat("/avatar " + avatar);
    }

    public static void type(int... keys) {
        for (int key : keys) {
            ROBOT.keyPress(key);
        }
        for (int key : keys) {
            ROBOT.keyRelease(key);
        }
    }

    public static void kick(int duration) {
        startKick();
        sleep(duration);
        stopKick();
    }

    public static void startKick() {
        ROBOT.keyPress(KeyEvent.VK_SPACE);
    }

    public static void stopKick() {
        ROBOT.keyRelease(KeyEvent.VK_SPACE);
    }

    public static void click(int duration) {
        startClick();
        sleep(duration);
        stopClick();
    }

    public static void startClick() {
        ROBOT.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    }

    public static void stopClick() {
        ROBOT.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    }

    public static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
