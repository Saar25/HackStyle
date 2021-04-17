module hackstyle {
    requires javafx.controls;
    requires jnativehook;
    requires reflections;
    requires java.xml.bind;
    requires java.desktop;
    requires java.logging;

    opens hackstyle.scripts to java.xml.bind;
    exports hackstyle;
}