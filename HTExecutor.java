package haxballTools;

class HTExecutor {

    private HTScript script;
    private int delay;

    HTExecutor(int delay) {
        this.delay = delay;
    }

    void start() {
        if (script != null)
            script.start(delay);
    }

    void stop() {
        if (script != null)
            script.stop();
    }

    void setScript(HTScript script) {
        this.script = script;
    }

    void setDelay(int delay) {
        this.delay = delay;
    }

}