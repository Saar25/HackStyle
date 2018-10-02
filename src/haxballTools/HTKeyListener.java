package haxballTools;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class HTKeyListener extends NativeKeyAdapter {

    private final HTExecutor executor;
    private final Map<Integer, Boolean> keys;

    public HTKeyListener(HTExecutor executor) {
        this.executor = executor;
        this.keys = new HashMap<>();

        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    public void addKey(int key) {
        if (!keys.containsKey(key)) {
            keys.put(key, false);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keys.containsKey(keyCode) && !keys.get(keyCode)) {
            executor.startScripts(keyCode);
            keys.put(keyCode, true);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keys.containsKey(keyCode) && keys.get(keyCode)) {
            executor.stopScripts(keyCode);
            keys.put(keyCode, false);
        }
    }
}
