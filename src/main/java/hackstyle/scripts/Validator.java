package hackstyle.scripts;

import hackstyle.scripts.exceptions.InvalidScriptCodeException;
import hackstyle.scripts.exceptions.InvalidScriptIndicatorException;

public final class Validator {

    private Validator() {
        throw new AssertionError("Cannot create instance of " + getClass().getSimpleName());
    }

    public static void validateIndicator(String indicator) throws InvalidScriptIndicatorException {
        if (indicator.length() != 1) {
            throw new InvalidScriptIndicatorException("Script indicator cannot be: " + indicator);
        }
    }

    public static void validateCode(String code) throws InvalidScriptCodeException {
        if (!code.startsWith("{") || !code.endsWith("}")) {
            throw new InvalidScriptCodeException("Script code should start with { and end with }");
        }
    }

}
