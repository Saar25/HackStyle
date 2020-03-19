package hackstyle.scripts;

public interface ScriptVariable {

    String get();

    interface Creator {
        ScriptVariable create();
    }
}
