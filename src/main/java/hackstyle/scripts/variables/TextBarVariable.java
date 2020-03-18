package hackstyle.scripts.variables;

import hackstyle.scripts.ScriptVariable;
import javafx.scene.control.TextField;

public class TextBarVariable implements ScriptVariable {

    private final TextField textField;

    public TextBarVariable(TextField textField) {
        this.textField = textField;
    }

    @Override
    public String get() {
        return textField.getText();
    }
}
