package haxballTools;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

interface HTScript{

    /**
     *
     * @param millis
     * The duration of the executing
     */
    void execute(long millis);

    Robot ROBOT = createRobot();

    static Robot createRobot(){
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            robot = null;
            System.err.println("Unable to create robot");
        }
        return robot;
    }

    HTScript SLEEP = millis -> {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    // Builtin macro for the game
    HTScript MACRO = millis -> {
        ROBOT.keyPress(KeyEvent.VK_SPACE);
        SLEEP.execute(millis);
        ROBOT.keyRelease(KeyEvent.VK_SPACE);
    };

    // Builtin clicker for the game
    HTScript CLICKER = millis -> {
        ROBOT.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        SLEEP.execute(millis);
        ROBOT.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    };

    // Builtin avatar change for the game
    //
    HTScript AVATAR = millis -> {
        final String avatar = HTGui.getTextFieldText();
        for (int i = 0 ; i < avatar.length() ; i+=2) {
            HTRobot.sendToChat("/avatar " +
                    (avatar.charAt(i) == ' ' ? "" : avatar.charAt(i)) +
                    (i+1 < avatar.length() ? avatar.charAt(i+1) : ""));
            SLEEP.execute(millis);
        }
    };

    // Builtin avatar changer for the game
    static HTScript AVATAR(String avatar){
        return millis -> {
            for (int i = 0 ; i < avatar.length() ; i+=2) {
                HTRobot.sendToChat("/avatar " +
                        (avatar.charAt(i) == ' ' ? "" : avatar.charAt(i)) +
                        (i+1 < avatar.length() ? avatar.charAt(i+1) : ""));
                SLEEP.execute(millis);
            }
        };
    }

    HTScript NOOP = millis -> { };
}
