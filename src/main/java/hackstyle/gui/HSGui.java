package hackstyle.gui;

import hackstyle.scripts.Script;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.util.List;

public class HSGui extends TabPane {

    private static TextField textField;
    private static ScrollBar scrollBar;

    public HSGui(List<Script> scripts) {
        textField = new TextField("");
        textField.setMaxWidth(200);
        textField.setStyle("-fx-font: 20px Tahoma;");

        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.HORIZONTAL);
        scrollBar.setPrefHeight(61);
        scrollBar.setValue(60);
        scrollBar.setMax(100);
        scrollBar.setMin(5);

        getTabs().add(new MainTab(scripts, scrollBar, textField));
        getTabs().add(new InternetTab());
    }

    public static double getScrollBarValue() {
        return scrollBar != null ? scrollBar.getValue() : 0;
    }

    public static String getTextFieldText() {
        return textField != null ? textField.getText() : "";
    }

    public void setTextFieldText(String s) {
        if (textField != null) textField.textProperty().setValue(s);
    }

    public void setScrollBarValue(double value) {
        if (scrollBar != null) scrollBar.setValue(value);
    }

}
