package haxballTools;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HaxballTools extends Application {

    @Override
    public void start(Stage primaryStage) {
        HTRobot clicker = new HTRobot(30);

        new HTKeyListener(clicker);
        Scene scene = new Scene(new HTGui(clicker), 700, 300);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Haxball Tools by Style");
        primaryStage.setOnCloseRequest(event -> { Platform.exit(); System.exit(0); });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
