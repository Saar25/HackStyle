package hackstyle.scripts;

import hackstyle.scripts.exceptions.ScriptParsingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScriptsFileParser {

    private final Map<String, ScriptAction> keywords;
    private final String code;

    public ScriptsFileParser(Map<String, ScriptAction> keywords, String code) {
        this.keywords = keywords;
        this.code = code;
    }

    public List<Script> parse() throws ScriptParsingException {
        final List<Script> scripts = new ArrayList<>();
        for (String scriptCode : code.split(",")) {
            scripts.add(parse(scriptCode));
        }
        return scripts;
    }

    private Script parse(String scriptCode) throws ScriptParsingException {
        final String[] split = scriptCode.split(" ", 2);
        final String[] settings = split[0].split(":");

        final String name = settings[0];

        final String indicator = settings[1];
        Validator.validateIndicator(indicator);

        final String code = split[1];
        Validator.validateCode(code);

        return null;
    }
}
