package hackstyle.gui;

import hackstyle.HaxballScript;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Map;

public class MainTab extends Tab {

    public MainTab(Map<String, HaxballScript> scripts, ScrollBar scrollBar, TextField textField) {
        super("Scripts");

        final VBox mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(20);

        final HBox checkBoxes = new HBox();
        checkBoxes.setAlignment(Pos.CENTER);
        checkBoxes.setSpacing(20);

        scripts.forEach((text, script) ->
                checkBoxes.getChildren().add(new ScriptCheckBox(text, script)));

        final Label label = new Label("Executions per second: " + (int) HSGui.getScrollBarValue());
        scrollBar.valueProperty().addListener(e -> label.setText("Executions per second: " + (int) HSGui.getScrollBarValue()));
        label.setTextFill(Color.WHITE);

        mainBox.getChildren().addAll(checkBoxes, textField, scrollBar, label);

        BorderPane mainScreen = new BorderPane();
        mainScreen.setCenter(mainBox);
        mainScreen.setStyle("-fx-background-color: black");

        setClosable(false);
        setContent(mainScreen);
    }
}
