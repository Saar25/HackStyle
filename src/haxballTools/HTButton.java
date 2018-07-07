package haxballTools;

import javafx.scene.control.RadioButton;

class HTButton extends RadioButton {

    private HTRobot htr;

    HTButton(HTRobot htr, String text, HTScript script){
        super(text);
        this.htr = htr;
        setScript(script);
        setMinSize(100,61);
        setSelected(false);
        setStyle("-fx-font: 20px Tahoma;");
    }

    void setScript(HTScript script) {
        setOnMouseClicked(event -> htr.setScript(script));
        if(getToggleGroup() != null && getToggleGroup().getSelectedToggle() == this) htr.setScript(script);
    }
}
