package haxballTools;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

class HTRobot {

    private Timer timer;

    private HTScript script = HTScript.MACRO;

    HTRobot(int delay) {

        timer = new Timer(delay, e -> script.execute(timer.getDelay()));
    }

    void setScript(HTScript script){ this.script = script; }

    void start() {
        timer.start();
    }

    void stop() { timer.stop(); }

    void setDelay(int delay) { timer.setDelay(delay); }

    static void sendToChat(String string) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(string);
        clipboard.setContents(stringSelection, stringSelection);

        try {
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            System.err.println("Unable to create robot");
        }
    }

}