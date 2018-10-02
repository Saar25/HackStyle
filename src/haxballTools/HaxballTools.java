package haxballTools;

import haxballTools.HTScripts.Avatar;
import haxballTools.HTScripts.Clicker;
import haxballTools.HTScripts.Macro;
import haxballTools.HTScripts.Spam;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class HaxballTools extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HTConfigs configs = new HTConfigs();
        try {
            configs.loadData("/configs.txt");
        } catch (Exception e) {
            System.err.println("Unable to load configs");
        }

        HTExecutor executor = new HTExecutor(60);
        HTKeyListener keyListener = new HTKeyListener(executor);
        Map<String, HTScript> scripts = new LinkedHashMap<>();

        int defaultIndicator = NativeKeyEvent.VC_E;
        int kicks = configs.getInt("KICKS", 2);
        String regex = configs.getString("REGEX", "/");

        int indicator = configs.getIndicator("PRESS", defaultIndicator);
        scripts.put("Press", Macro.createEndless(indicator));
        keyListener.addKey(indicator);

        indicator = configs.getIndicator("DOUBLE", defaultIndicator);
        scripts.put("Double", Macro.create(kicks, indicator));
        keyListener.addKey(indicator);

        indicator = configs.getIndicator("CLICK", defaultIndicator);
        scripts.put("Click", Clicker.create(indicator));
        keyListener.addKey(indicator);

        indicator = configs.getIndicator("SPAM", defaultIndicator);
        scripts.put("Spam", Spam.fromGUI(regex, indicator));
        keyListener.addKey(indicator);

        indicator = configs.getIndicator("AVATAR", defaultIndicator);
        scripts.put("Avatar", Avatar.fromGUI(indicator));
        keyListener.addKey(indicator);

        Scene scene = new Scene(new HTGui(executor, scripts), 700, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HackStyle by Style");
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }
}