package hackstyle;

import hackstyle.gui.HSGui;
import hackstyle.scripts.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class HackStyle extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HSConfigs configs = new HSConfigs();
        try {
            configs.loadData("/configs.txt");
        } catch (Exception e) {
            System.err.println("Unable to load configs");
        }

        int defaultIndicator = NativeKeyEvent.VC_E;
        int kicks = configs.getInt("KICKS", 2);
        String regex = configs.getString("REGEX", "/");

        Map<String, HaxballScript> scripts = new LinkedHashMap<>();
        scripts.put("Press" , Macro.createEndless(configs.getIndicator("PRESS" , defaultIndicator)));
        scripts.put("Double", Macro.create(kicks, configs.getIndicator("DOUBLE", defaultIndicator)));
        scripts.put("Click" , Clicker.create(     configs.getIndicator("CLICK" , defaultIndicator)));
        scripts.put("Spam"  , Spam.fromGUI(regex, configs.getIndicator("SPAM"  , defaultIndicator)));
        scripts.put("Avatar", Avatar.fromGUI(     configs.getIndicator("AVATAR", defaultIndicator)));

        Keyboard.init();
        final HSGui gui = new HSGui(scripts);
        gui.setTextFieldText(configs.getString("DEFAULT AVATAR", ""));

        Scene scene = new Scene(gui, 700, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HackStyle by Style");
        primaryStage.setOnCloseRequest(event -> {
            Keyboard.destroy();
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }
}