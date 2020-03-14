package hackstyle.scripts.exceptions;

public class ScriptParsingException extends Exception {

    public ScriptParsingException() {
    }

    public ScriptParsingException(String message) {
        super(message);
    }

    public ScriptParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScriptParsingException(Throwable cause) {
        super(cause);
    }

    public ScriptParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
