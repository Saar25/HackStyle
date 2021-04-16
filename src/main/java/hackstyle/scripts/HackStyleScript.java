package hackstyle.scripts;

public interface HackStyleScript {

    String name();

    char indicator();

    void execute(ScriptInput input);

    void stop();
}
