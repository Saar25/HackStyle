package hackstyle.gui;

import hackstyle.HSConfigs;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SettingsTab extends Tab {

    private final HSConfigs configs;
    private final HBox box = new HBox();

    public SettingsTab(HSConfigs configs) {
        super("Settings");
        this.configs = configs;
        setClosable(false);

        final VBox main = new VBox();
        final Label label = new Label("Restart to apply changes");
        label.setTextFill(Color.WHITE);
        main.setSpacing(20);
        main.setAlignment(Pos.CENTER);
        main.getChildren().add(box);
        main.getChildren().add(label);
        setContent(main);

        main.setStyle("-fx-background-color: black");
        box.setSpacing(50);
        box.setAlignment(Pos.CENTER);
        addProperties("Split", "Press", "Double", "Click", "Spam",
                "Avatar", "Default Speed", "Default Avatar");
    }

    private void addProperties(String... properties) {
        int i = 0;
        VBox settingsLine = new VBox();
        box.getChildren().add(settingsLine);
        settingsLine.setAlignment(Pos.CENTER_LEFT);
        settingsLine.setSpacing(10);

        for (String name : properties) {
            final Label label = new Label(name + ":");

            final String property = name.toUpperCase();
            final TextField field = new TextField(configs.getString(property));
            field.setMaxWidth(100);
            field.textProperty().addListener((o, old, value) ->
                    configs.set(property, value)
            );

            final HBox setting = new HBox();
            setting.getChildren().add(label);
            setting.getChildren().add(field);
            setting.setAlignment(Pos.CENTER_RIGHT);
            setting.setStyle("-fx-font: 20px Tahoma;");
            setting.setSpacing(20);

            if (i == properties.length / 2) {
                settingsLine = new VBox();
                settingsLine.setAlignment(Pos.CENTER_RIGHT);
                settingsLine.setSpacing(10);
                box.getChildren().add(settingsLine);
            }

            settingsLine.getChildren().add(setting);
            i++;
        }
    }
}
