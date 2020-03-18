package hackstyle.scripts.variables;

import hackstyle.scripts.ScriptVariable;
import javafx.scene.control.ScrollBar;

public class ScrollBarVariable implements ScriptVariable {

    private final ScrollBar scrollBar;

    public ScrollBarVariable(ScrollBar scrollBar) {
        this.scrollBar = scrollBar;
    }

    @Override
    public String get() {
        final double value = 1000 / scrollBar.getValue();
        return String.valueOf((int) value);
    }
}
