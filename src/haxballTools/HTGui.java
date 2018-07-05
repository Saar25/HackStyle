package haxballTools;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class HTGui extends BorderPane {

    HTGui(HTRobot htr) {
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        VBox vBox = new VBox();
        vBox.setSpacing(20);

        ToggleGroup buttons = new ToggleGroup();

        RadioButton toPress = new RadioButton("Press");
        toPress.setOnMouseClicked(event -> htr.toPress());
        toPress.setMinSize(100,61);
        toPress.setStyle("-fx-font: 20px Tahoma;");
        toPress.setToggleGroup(buttons);
        toPress.setSelected(true);

        RadioButton toClick = new RadioButton("Click");
        toClick.setOnMouseClicked(event -> htr.toClick());
        toClick.setMinSize(100,61);
        toClick.setStyle("-fx-font: 20px Tahoma;");
        toClick.setToggleGroup(buttons);

        RadioButton toAvatar = new RadioButton("Avatar");
        toAvatar.setOnMouseClicked(event -> htr.toAvatar());
        toAvatar.setMinSize(100,61);
        toAvatar.setStyle("-fx-font: 20px Tahoma;");
        toAvatar.setToggleGroup(buttons);

        RadioButton toEmpty = new RadioButton("Empty");
        toEmpty.setOnMouseClicked(event -> htr.toEmpty());
        toEmpty.setMinSize(100,61);
        toEmpty.setStyle("-fx-font: 20px Tahoma;");
        toEmpty.setToggleGroup(buttons);

        TextField setAvatar = new TextField();
        setAvatar.setOnAction(event -> htr.setAvatar(setAvatar.getText()));
        setAvatar.setText("S t y l e S");
        setAvatar.setMaxWidth(200);
        setAvatar.setStyle("-fx-font: 20px Tahoma;");

        ScrollBar getDelay = new ScrollBar();
        getDelay.setOrientation(Orientation.HORIZONTAL);
        getDelay.valueProperty().addListener(e -> htr.setDelay((int)getDelay.getValue()));
        getDelay.setPrefHeight(61);
        getDelay.setValue(70);
        getDelay.setMax(100);
        getDelay.setMin(10);

        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(toPress, toClick, toAvatar, toEmpty);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBox, setAvatar, getDelay);

        setCenter(vBox);
        setStyle("-fx-background-color: black");
    }
}
