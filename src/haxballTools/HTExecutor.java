package haxballTools;

import java.util.ArrayList;

class HTExecutor {

    private ArrayList<HTScript> scripts;
    private int duration;

    HTExecutor(int delay) {
        this.duration = delay;
        scripts = new ArrayList<>();
    }

    void startScripts(int key) {
        scripts.forEach(s -> {
            if (s != null && s.getIndicator() == key) s.start(duration);
        });
    }

    void stopScripts(int key) {
        scripts.forEach(s -> {
            if (s != null && s.getIndicator() == key) s.stop();
        });
    }

    void addScript(HTScript script) {
        scripts.add(script);
    }

    void removeScript(HTScript script) {
        scripts.remove(script);
    }

    void setDuration(int duration) {
        this.duration = duration;
    }

}