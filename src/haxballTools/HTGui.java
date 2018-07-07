package haxballTools;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Map;

class HTGui extends BorderPane {

    private static ScrollBar scrollBar;
    private static TextField textField;
    private ArrayList<HTButton> buttons;

    HTGui(HTRobot htr, Map<String, HTScript> scripts) {

        HBox hBox = new HBox();
        VBox vBox = new VBox();

        ToggleGroup toggleGroup = new ToggleGroup();
        buttons = new ArrayList<>();

        scripts.forEach((string, script) -> {
            HTButton button = new HTButton(htr, string, script);
            button.setToggleGroup(toggleGroup);
            buttons.add(button);
        });

        textField = new TextField("");
        textField.setOnAction(event ->
                buttons.forEach(b -> {
                    if (b.getText().equals("Avatar"))
                        b.setScript(HTScript.AVATAR(textField.getText()));
                })
        );
        textField.setMaxWidth(200);
        textField.setStyle("-fx-font: 20px Tahoma;");

        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.HORIZONTAL);
        scrollBar.valueProperty().addListener(e -> htr.setDelay((int) scrollBar.getValue()));
        scrollBar.setPrefHeight(61);
        scrollBar.setValue(60);
        scrollBar.setMax(100);
        scrollBar.setMin(5);

        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(buttons);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBox, textField, scrollBar);

        setCenter(vBox);
        setStyle("-fx-background-color: black");
    }

    static double getScrollBarValue(){
        return scrollBar.getValue();
    }

    static String getTextFieldText(){
        return textField.getText();
    }
}
