package hackstyle.keyboard;

import hackstyle.ErrorMessage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Keyboard extends NativeKeyAdapter {

    private static final Keyboard KEYBOARD = new Keyboard();

    private final List<Integer> pressedKeys;
    private final List<EventListener<NativeKeyEvent>> listeners;

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

    public static void addListener(EventListener<NativeKeyEvent> listener) {
        KEYBOARD.listeners.add(listener);
    }

    public static OnAction<NativeKeyEvent> onKeyPress(int key) {
        return listener -> Keyboard.addListener(event -> {
            if (event.getKeyCode() == key && isPressed(key)) {
                listener.listen(event);
            }
        });
    }

    public static OnAction<NativeKeyEvent> onKeyRelease(int key) {
        return listener -> Keyboard.addListener(event -> {
            if (event.getKeyCode() == key && !isPressed(key)) {
                listener.listen(event);
            }
        });
    }

    public static boolean isPressed(Integer key) {
        return KEYBOARD.pressedKeys.contains(key);
    }

    private void notifyListeners(NativeKeyEvent e) {
        listeners.forEach(listener -> listener.listen(e));
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        final int keyCode = e.getKeyCode();
        if (!pressedKeys.contains(keyCode)) {
            pressedKeys.add(keyCode);
            notifyListeners(e);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        final int keyCode = e.getKeyCode();
        if (pressedKeys.contains(keyCode)) {
            pressedKeys.remove((Integer) keyCode);
            notifyListeners(e);
        }
    }

    public static void destroy() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }
}
