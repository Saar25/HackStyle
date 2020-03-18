package hackstyle.scripts.exceptions;

public class InvalidScriptActionException extends ScriptParsingException {

    public InvalidScriptActionException() {
    }

    public InvalidScriptActionException(String message) {
        super(message);
    }

    public InvalidScriptActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidScriptActionException(Throwable cause) {
        super(cause);
    }

    public InvalidScriptActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
