package hackstyle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HSConfigs {

    private final Map<String, String> configs;

    public HSConfigs() {
        this.configs = new HashMap<>();
    }

    public void loadData(String fileName) throws Exception {
        List<String> list = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Class.class.getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
        for (String string : list) {
            String[] s = string.split(";");
            if (s.length >= 2) {
                configs.put(s[0], s[1]);
            }
        }
    }

    public int getIndicator(String name, int defaultIndicator) {
        return KeyboardUtils.parseCharToKey(getString(name, " ").charAt(0), defaultIndicator);
    }

    public int getInt(String name, int defaultValue) {
        return Integer.parseInt(getString(name, defaultValue + ""));
    }

    public String getString(String name, String defaultValue) {
        return configs.getOrDefault(name, defaultValue);
    }

}
