package haxballTools;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

class HTRobot {

    private final int key = KeyEvent.VK_SPACE;
    private final int button = MouseEvent.BUTTON1_DOWN_MASK;

    private Robot robot;
    private Timer timer;
    private String action = "press";
    private String avatar = "";

    private boolean isPressing = false;
    private boolean isClicking = false;

    HTRobot(int delay) {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.println("Unable to create robot");
            System.exit(1);
        }

        ActionListener act = e -> {
            switch (action) {
                case "press":
                    if (isPressing)
                        robot.keyRelease(key);
                    else robot.keyPress(key);
                    isPressing = !isPressing;
                    break;
                case "click":
                    if (isClicking)
                        robot.mouseRelease(button);
                    else robot.mousePress(button);
                    isClicking = !isClicking;
                    break;
                case "avatar":
                    for (int i = 0 ; i < avatar.length() ; i+=2) {
                        sendToChat("/avatar " + avatar.charAt(i) + (i+1 < avatar.length() ? avatar.charAt(i+1) : ""));
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                    timer.stop();
                default:

            }
        };

        timer = new Timer(delay, act);
    }

    void toPress() {
        action = "press";
    }

    void toClick() {
        action = "click";
    }

    void toAvatar() { action = "avatar"; }

    void toEmpty(){ action = ""; }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
        robot.mouseRelease(button);
        robot.keyRelease(key);
        isClicking = isPressing = false;
    }

    void setDelay(int delay) {
        timer.setDelay(delay);
    }

    void setAvatar(String avatar) { this.avatar = avatar; }


    private void sendToChat(String s) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(s);
        clipboard.setContents(stringSelection, stringSelection);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}