package haxballTools;

import javafx.scene.control.RadioButton;

class HTButton extends RadioButton {

    private HTExecutor executor;

    HTButton(HTExecutor executor, String text, HTScript script) {
        super(text);
        this.executor = executor;
        setScript(script);
        setMinSize(100, 61);
        setSelected(false);
        setStyle("-fx-font: 20px Tahoma;");
    }

    private void setScript(HTScript script) {
        setOnMouseClicked(event -> executor.setScript(script));
        if (getToggleGroup() != null && getToggleGroup().getSelectedToggle() == this) executor.setScript(script);
    }
}
