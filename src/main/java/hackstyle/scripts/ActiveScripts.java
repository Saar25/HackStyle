package hackstyle.scripts;

import java.util.LinkedList;
import java.util.List;

public class ActiveScripts {

    private final List<HackStyleScript> activeScripts = new LinkedList<>();

    public List<HackStyleScript> get() {
        return this.activeScripts;
    }

    public void setActive(HackStyleScript script, boolean active) {
        if (active && !get().contains(script)) {
            this.activeScripts.add(script);
        } else if (get().contains(script)) {
            this.activeScripts.remove(script);
        }
    }
}
