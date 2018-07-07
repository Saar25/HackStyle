package haxballTools;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class HaxballTools extends Application {

    @Override
    public void start(Stage primaryStage) {

        /*
         * The scripts that will be shown on the GUI as toggle buttons
         * To add a script:
         *      scripts.put("name", m -> {
         *              some code
         *          }
         *      );
         */
        Map<String, HTScript> scripts = new LinkedHashMap<>();
        scripts.put("Press", HTScript.MACRO);
        scripts.put("Click", HTScript.CLICKER);
        scripts.put("Avatar", HTScript.AVATAR);
        scripts.put("Empty", HTScript.NOOP);

        HTRobot robot = new HTRobot(60);
        new HTKeyListener(robot);
        Scene scene = new Scene(new HTGui(robot, scripts), 700, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("HackStyle by Style");
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}