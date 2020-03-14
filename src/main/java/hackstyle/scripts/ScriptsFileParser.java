package hackstyle.scripts;

import java.util.ArrayList;
import java.util.List;

public class ScriptsFileParser {

    private final String code;

    public ScriptsFileParser(String code) {
        this.code = code;
    }

    public List<Script> parse() {
        final List<Script> scripts = new ArrayList<>();
        for (String scriptCode : code.split(",")) {
            scripts.add(parse(scriptCode));
        }
        return scripts;
    }

    private Script parse(String scriptCode) {
        final String[] split = scriptCode.split(" ", 2);
        final String[] settings = split[0].split(":");

        final String name = settings[0];
        final String key = settings[1];
        final String code = split[1];


    }
}
