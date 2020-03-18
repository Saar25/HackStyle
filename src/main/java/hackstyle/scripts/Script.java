package hackstyle.scripts;

public interface Script {

    String name();

    char indicator();

    void start();

    void stop();
}
