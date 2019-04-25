package hackstyle.gui;

import hackstyle.HaxballScript;
import javafx.scene.control.CheckBox;

public class ScriptCheckBox extends CheckBox {

    public ScriptCheckBox(String text, HaxballScript script) {
        super(text);
        setMinSize(100, 61);
        setSelected(false);
        setStyle("-fx-font: 20px Tahoma;");
        selectedProperty().addListener((o, l, isCheck) ->
                script.setActive(isCheck));
    }

}
