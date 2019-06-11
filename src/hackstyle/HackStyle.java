package hackstyle;

import hackstyle.gui.HSGui;
import hackstyle.scripts.*;
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

        HSConfigs configs = new HSConfigs("configs.txt");
        try {
            configs.loadData();
        } catch (Exception e) {
            System.err.println("Unable to load configs");
        }

        String s = configs.getString("SPLIT");

        Map<String, HaxballScript> scripts = new LinkedHashMap<>();
        scripts.put("Press" , Macro.endless(    configs.getIndicator("PRESS")));
        scripts.put("Double", Macro.create(2,   configs.getIndicator("DOUBLE")));
        scripts.put("Click" , Clicker.create(   configs.getIndicator("CLICK")));
        scripts.put("Spam"  , Spam.fromGUI(s,   configs.getIndicator("SPAM")));
        scripts.put("Avatar", Avatar.fromGUI(   configs.getIndicator("AVATAR")));

        Keyboard.init();
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