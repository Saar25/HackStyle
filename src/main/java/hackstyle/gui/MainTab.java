package hackstyle.gui;

import hackstyle.scripts.ActiveScripts;
import hackstyle.scripts.Script;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainTab extends Tab {

    private final ActiveScripts activeScripts = new ActiveScripts();
    private final TextField textField;
    private final ScrollBar scrollBar;
    private final HBox checkBoxes;

    public MainTab() {
        super("Scripts");

        textField = new TextField("");
        textField.setMaxWidth(200);
        textField.setStyle("-fx-font: 20px Tahoma;");

        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.HORIZONTAL);
        scrollBar.setPrefHeight(61);
        scrollBar.setValue(60);
        scrollBar.setMax(100);
        scrollBar.setMin(5);

        final VBox mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(20);

        this.checkBoxes = new HBox();
        checkBoxes.setAlignment(Pos.CENTER);
        checkBoxes.setSpacing(20);

        final Label label = new Label("Executions per second: " + scrollBar.getValue());
        scrollBar.valueProperty().addListener(e -> label.setText(
                "Executions per second: " + (int) scrollBar.getValue()));
        label.setTextFill(Color.WHITE);

        mainBox.getChildren().addAll(checkBoxes, textField, scrollBar, label);

        BorderPane mainScreen = new BorderPane();
        mainScreen.setCenter(mainBox);
        mainScreen.setStyle("-fx-background-color: black");

        setClosable(false);
        setContent(mainScreen);
    }

    public void addScript(Script script) {
        checkBoxes.getChildren().add(new ScriptCheckBox(activeScripts, script));
    }

    public ActiveScripts getActiveScripts() {
        return this.activeScripts;
    }

    public TextField getTextField() {
        return textField;
    }

    public ScrollBar getScrollBar() {
        return scrollBar;
    }
}
