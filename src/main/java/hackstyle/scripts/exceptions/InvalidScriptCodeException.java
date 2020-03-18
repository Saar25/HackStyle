package hackstyle.scripts.exceptions;

public class InvalidScriptCodeException extends ScriptParsingException {

    public InvalidScriptCodeException() {
    }

    public InvalidScriptCodeException(String message) {
        super(message);
    }

    public InvalidScriptCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidScriptCodeException(Throwable cause) {
        super(cause);
    }

    public InvalidScriptCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
