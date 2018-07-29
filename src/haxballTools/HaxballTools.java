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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class HaxballTools extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        Map<String, HTScript> scripts = new LinkedHashMap<>();
        scripts.put("Press", Macro.create());
        scripts.put("Double", Macro.create(2));
        scripts.put("Click", Clicker.create());
        scripts.put("Spam", Spam.splitFromGUI());
        scripts.put("Avatar", Avatar.fromGUI());

        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(HTScript.DEFAULT_INDICATOR);

        HTExecutor executor = new HTExecutor(60);
        new HTKeyListener(executor, indicators);
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