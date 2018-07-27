package haxballTools;

import haxballTools.HTScripts.Avatar;
import haxballTools.HTScripts.Clicker;
import haxballTools.HTScripts.Macro;
import haxballTools.HTScripts.Spam;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        scripts.put("Click", Clicker.create());
        scripts.put("Spam", Spam.splitFromGUI());
        scripts.put("Avatar", Avatar.fromGUI());
        scripts.put("Empty", null);

        HTExecutor executor = new HTExecutor(60);
        new HTKeyListener(executor);
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