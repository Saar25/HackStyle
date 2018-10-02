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

        int defaultIndicator = NativeKeyEvent.VC_E;
        int kicks = configs.getInt("KICKS", 2);
        String regex = configs.getString("REGEX", "/");

        Map<String, HTScript> scripts = new LinkedHashMap<>();
        scripts.put("Press" , Macro.createEndless(configs.getIndicator("PRESS" , defaultIndicator)));
        scripts.put("Double", Macro.create(kicks, configs.getIndicator("DOUBLE", defaultIndicator)));
        scripts.put("Click" , Clicker.create(     configs.getIndicator("CLICK" , defaultIndicator)));
        scripts.put("Spam"  , Spam.fromGUI(regex, configs.getIndicator("SPAM"  , defaultIndicator)));
        scripts.put("Avatar", Avatar.fromGUI(     configs.getIndicator("AVATAR", defaultIndicator)));
        scripts.values().forEach((s) -> keyListener.addKey(s.getIndicator()));

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