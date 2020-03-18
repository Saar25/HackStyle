package hackstyle.scripts.exceptions;

public class InvalidScriptIndicatorException extends ScriptParsingException {

    public InvalidScriptIndicatorException() {
    }

    public InvalidScriptIndicatorException(String message) {
        super(message);
    }

    public InvalidScriptIndicatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidScriptIndicatorException(Throwable cause) {
        super(cause);
    }

    public InvalidScriptIndicatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
