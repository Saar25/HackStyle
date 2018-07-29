package haxballTools;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class HTKeyListener implements NativeKeyListener {

    private HTExecutor executor;

    private ArrayList<Integer> keys;
    private boolean pressing = false;

    HTKeyListener(HTExecutor executor, ArrayList<Integer> keys) {
        this.executor = executor;
        this.keys = keys;

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

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (!pressing && keys.contains(e.getKeyCode())) {
            executor.startScripts(e.getKeyCode());
            pressing = true;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (keys.contains(e.getKeyCode())) {
            executor.stopScripts(e.getKeyCode());
            pressing = false;
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }
}
