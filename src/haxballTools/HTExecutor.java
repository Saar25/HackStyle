package haxballTools;

import java.util.ArrayList;

class HTExecutor {

    private ArrayList<HTScript> scripts;
    private int duration;

    public HTExecutor(int duration) {
        this.scripts = new ArrayList<>();
        this.duration = duration;
    }

    public void startScripts(int key) {
        for (HTScript s : scripts) {
            if (s != null && s.getIndicator() == key) {
                s.start(duration);
            }
        }
    }

    public void stopScripts(int key) {
        for (HTScript s : scripts) {
            if (s != null && s.getIndicator() == key) {
                s.stop();
            }
        }
    }

    public void addScript(HTScript script) {
        this.scripts.add(script);
    }

    public void removeScript(HTScript script) {
        this.scripts.remove(script);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}