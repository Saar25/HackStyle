package haxballTools;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public final class HTRobot {

    private static final Robot ROBOT = createRobot();

    private HTRobot() {

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
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(string);
        clipboard.setContents(stringSelection, stringSelection);

        ROBOT.keyPress(KeyEvent.VK_TAB);
        ROBOT.keyRelease(KeyEvent.VK_TAB);
        ROBOT.keyPress(KeyEvent.VK_CONTROL);
        ROBOT.keyPress(KeyEvent.VK_V);
        ROBOT.keyRelease(KeyEvent.VK_V);
        ROBOT.keyRelease(KeyEvent.VK_CONTROL);
        ROBOT.keyPress(KeyEvent.VK_ENTER);
        ROBOT.keyRelease(KeyEvent.VK_ENTER);

    }

    public static void kick(int duration) {
        ROBOT.keyPress(KeyEvent.VK_SPACE);
        sleep(duration);
        ROBOT.keyRelease(KeyEvent.VK_SPACE);
    }

    public static void click(int duration) {
        ROBOT.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        sleep(duration);
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
