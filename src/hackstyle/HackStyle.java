package hackstyle;

import hackstyle.gui.HSGui;
import hackstyle.scripts.Avatar;
import hackstyle.scripts.Clicker;
import hackstyle.scripts.Macro;
import hackstyle.scripts.Spam;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class HackStyle extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HSConfigs configs = new HSConfigs("HackStyleConfigs.txt");

        try {
            configs.loadData();
        } catch (Exception e) {
            configs.set("SPLIT", "/");
            configs.set("PRESS", "R");
            configs.set("DOUBLE", "R");
            configs.set("CLICK", "R");
            configs.set("SPAM", "R");
            configs.set("AVATAR", "R");
            configs.set("DEFAULT AVATAR", ":}");
            configs.set("DEFAULT SPEED", "60");
            configs.updateFile();
        }

        Keyboard.init();

        String s = configs.getString("SPLIT");
        Map<String, HaxballScript> scripts = new LinkedHashMap<>();
        scripts.put("Press", Macro.endless(configs.getIndicator("PRESS")));
        scripts.put("Double", Macro.create(2, configs.getIndicator("DOUBLE")));
        scripts.put("Click", Clicker.create(configs.getIndicator("CLICK")));
        scripts.put("Spam", Spam.fromGUI(s, configs.getIndicator("SPAM")));
        scripts.put("Avatar", Avatar.fromGUI(configs.getIndicator("AVATAR")));

        final HSGui gui = new HSGui(configs, scripts);
        gui.setTextFieldText(configs.getString("DEFAULT AVATAR"));
        gui.setScrollBarValue(configs.getInt("DEFAULT SPEED"));

        Scene scene = new Scene(gui, 700, 300);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HackStyle by Style");
        primaryStage.setOnCloseRequest(event -> {
            configs.updateFile();
            Keyboard.destroy();
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }
}