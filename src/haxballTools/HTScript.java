package haxballTools;

import org.jnativehook.keyboard.NativeKeyEvent;

public interface HTScript {

    int DEFAULT_INDICATOR = NativeKeyEvent.VC_E;

    void start(int duration);

    default void stop(){

    }

    default int getIndicator() {
        return DEFAULT_INDICATOR;
    }

}