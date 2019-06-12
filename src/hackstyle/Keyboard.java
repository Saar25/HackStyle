package hackstyle;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class Keyboard extends NativeKeyAdapter {

    private static final Keyboard KEYBOARD = new Keyboard();

    private final List<Integer> pressedKeys;
    private final List<Listener> listeners;

    private Keyboard() {
        this.pressedKeys = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    public static void init() {
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        registerNativeHook();
        GlobalScreen.addNativeKeyListener(KEYBOARD);
    }

    private static void registerNativeHook() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            ErrorMessage.createErrorFile(e, "HackStyleError");
        }
    }

    public static void addListener(Listener listener) {
        KEYBOARD.listeners.add(listener);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();
        if (pressedKeys.contains(keyCode)) {
            return;
        } else {
            pressedKeys.add(keyCode);
        }
        for (Listener listener : listeners) {
            listener.handle(keyCode, true);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();
        if (!pressedKeys.contains(keyCode)) {
            return;
        } else {
            pressedKeys.remove((Integer) keyCode);
        }
        for (Listener listener : listeners) {
            listener.handle(keyCode, false);
        }
    }

    public static void destroy() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    public interface Listener {

        void handle(int key, boolean isDown);

    }
}
