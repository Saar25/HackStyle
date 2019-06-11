package hackstyle.gui;

import hackstyle.internet.Connection;
import hackstyle.internet.Netstat;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.util.List;

public class InternetTab extends Tab {

    private Netstat before = null;
    private Netstat netstat = null;

    public InternetTab() {
        super("Internet");

        final VBox mainBox = new VBox();
        mainBox.setStyle("-fx-background-color: black");
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(20);

        final HBox buttons = new HBox();
        buttons.setStyle("-fx-background-color: black");
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);

        final TextArea textArea = new TextArea();
        textArea.setStyle("-fx-font: 16px Tahoma;");
        textArea.setMaxSize(600, 100);
        textArea.setEditable(false);

        final ToggleButton listenBtn = new ToggleButton("Listen to new connections");
        listenBtn.setStyle("-fx-font: 20px Tahoma;");
        listenBtn.setMinSize(100, 61);
        mainBox.getChildren().add(listenBtn);


        final Timer timer = new Timer(500, e -> {
            netstat = Netstat.current();
            List<Connection> connections = getNewConnections();
            if (connections.size() > 0) {
                textArea.appendText(connections.toString() + "\n");
            }
            before = Netstat.current();

        });
        listenBtn.selectedProperty().addListener((o, old, value) -> {
            if (value) {
                before = Netstat.current();
                textArea.appendText("Started listening to new connections...\n");
                timer.start();
            } else {
                textArea.appendText("Stopped listening to new connections.\n");
                timer.stop();
            }
        });

        mainBox.getChildren().addAll(buttons, textArea);

        setClosable(false);
        setContent(mainBox);
    }

    private List<Connection> getNewConnections() {
        return netstat.differenceFrom(before);
    }
}
