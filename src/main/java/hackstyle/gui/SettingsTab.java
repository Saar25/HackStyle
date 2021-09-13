package hackstyle.gui;

import hackstyle.settings.HackStyleSettings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SettingsTab extends Tab {

    public SettingsTab(HackStyleSettings settings) {
        super("Settings");

        final VBox main = new VBox();
        main.setAlignment(Pos.CENTER);
        main.setSpacing(20);

        final HBox content = addIndicatorChangers(settings);
        content.setAlignment(Pos.CENTER);
        content.setSpacing(50);
        main.getChildren().add(content);

        final Label label = new Label("Restart to apply changes");
        label.setTextFill(Color.WHITE);
        main.getChildren().add(label);

        main.setStyle("-fx-background-color: black");

        setClosable(false);
        setContent(main);
    }

    private HBox addIndicatorChangers(HackStyleSettings settings) {
        final HBox container = new HBox();

        final List<HBox> hBoxes = settings.scripts.stream().map(this::scriptToUIBox).collect(Collectors.toList());
        final List<String> requiredValues = Arrays.asList("DEFAULT-AVATAR", "DEFAULT-SLIDER", "DEFAULT-TEXTFIELD");
        hBoxes.addAll(settings.values.stream().filter(v -> requiredValues.contains(v.name)).map(this::valueToUIBox).collect(Collectors.toList()));

        final VBox left = new VBox();
        left.setAlignment(Pos.TOP_CENTER);
        left.setSpacing(10);
        container.getChildren().add(left);

        final VBox right = new VBox();
        right.setAlignment(Pos.TOP_CENTER);
        right.setSpacing(10);
        container.getChildren().add(right);

        for (int i = 0; i < hBoxes.size(); i += 2) {
            left.getChildren().add(hBoxes.get(i));
        }
        for (int i = 1; i < hBoxes.size(); i += 2) {
            right.getChildren().add(hBoxes.get(i));
        }

        return container;
    }

    private HBox scriptToUIBox(HackStyleSettings.Script script) {
        final Label label = new Label(script.title + ":");

        final TextField field = new TextField(script.indicator);
        field.setMaxWidth(100);
        field.textProperty().addListener((o, old, value) -> {
            if (value.length() > 1) {
                field.setText(value.substring(0, 1));
            } else if (value.length() == 1 && value.charAt(0) >= 'a' && value.charAt(0) <= 'z') {
                field.setText(value.toUpperCase());
            } else if (value.length() == 1 && (value.charAt(0) < 'A' || value.charAt(0) > 'Z')) {
                field.setText(old);
            } else {
                script.indicator = value.toUpperCase();
            }
        });

        final HBox settingBox = new HBox();
        settingBox.getChildren().add(label);
        settingBox.getChildren().add(field);
        settingBox.setAlignment(Pos.CENTER_RIGHT);
        settingBox.setStyle("-fx-font: 20px Tahoma;");
        settingBox.setSpacing(20);

        return settingBox;
    }

    private HBox valueToUIBox(HackStyleSettings.Value value) {
        final Label label = new Label(value.name.substring("DEFAULT-".length()) + ":");

        final TextField field = new TextField(value.content);
        field.setMaxWidth(100);
        field.textProperty().addListener((o, old, newValue) -> {
            if (value.name.equals("DEFAULT-AVATAR") && newValue.length() > 2) {
                field.setText(newValue.substring(0, 2));
            } else if (value.name.equals("DEFAULT-TEXTFIELD") && newValue.length() > 10) {
                field.setText(newValue.substring(0, 10));
            } else if (value.name.equals("DEFAULT-SLIDER") && !isNumeric(newValue)) {
                field.setText(old);
            } else {
                value.content = newValue.toUpperCase();
            }
        });

        final HBox settingBox = new HBox();
        settingBox.getChildren().add(label);
        settingBox.getChildren().add(field);
        settingBox.setAlignment(Pos.CENTER_RIGHT);
        settingBox.setStyle("-fx-font: 20px Tahoma;");
        settingBox.setSpacing(20);

        return settingBox;
    }

    private boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
