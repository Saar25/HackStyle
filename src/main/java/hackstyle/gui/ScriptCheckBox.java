package hackstyle.gui;

import hackstyle.scripts.ActiveScripts;
import hackstyle.scripts.Script;
import javafx.scene.control.CheckBox;

public class ScriptCheckBox extends CheckBox {

    public ScriptCheckBox(ActiveScripts activeScripts, Script script) {
        super(script.name());
        setMinSize(100, 61);
        setSelected(false);
        setStyle("-fx-font: 20px Tahoma;");
        selectedProperty().addListener((o, l, isCheck) ->
                activeScripts.setActive(script, isCheck));
    }

}
