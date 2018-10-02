package haxballTools;

import org.jnativehook.keyboard.NativeKeyEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HTConfigs {

    private final Map<String, String> configs;

    public HTConfigs() {
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
        return parseCharToKey(getString(name, " ").charAt(0), defaultIndicator);
    }

    public int getInt(String name, int defaultValue) {
        return Integer.parseInt(getString(name, defaultValue + ""));
    }

    public String getString(String name, String defaultValue) {
        return configs.getOrDefault(name, defaultValue);
    }

    private static int parseCharToKey(char c, int defaultIndicator) {
        switch (c) {
            case 'A':
                return NativeKeyEvent.VC_A;
            case 'B':
                return NativeKeyEvent.VC_B;
            case 'C':
                return NativeKeyEvent.VC_C;
            case 'D':
                return NativeKeyEvent.VC_D;
            case 'E':
                return NativeKeyEvent.VC_E;
            case 'F':
                return NativeKeyEvent.VC_F;
            case 'G':
                return NativeKeyEvent.VC_G;
            case 'H':
                return NativeKeyEvent.VC_H;
            case 'I':
                return NativeKeyEvent.VC_I;
            case 'J':
                return NativeKeyEvent.VC_J;
            case 'K':
                return NativeKeyEvent.VC_K;
            case 'L':
                return NativeKeyEvent.VC_L;
            case 'M':
                return NativeKeyEvent.VC_M;
            case 'N':
                return NativeKeyEvent.VC_N;
            case 'O':
                return NativeKeyEvent.VC_O;
            case 'P':
                return NativeKeyEvent.VC_P;
            case 'Q':
                return NativeKeyEvent.VC_Q;
            case 'R':
                return NativeKeyEvent.VC_R;
            case 'S':
                return NativeKeyEvent.VC_S;
            case 'T':
                return NativeKeyEvent.VC_T;
            case 'U':
                return NativeKeyEvent.VC_U;
            case 'V':
                return NativeKeyEvent.VC_V;
            case 'W':
                return NativeKeyEvent.VC_W;
            case 'X':
                return NativeKeyEvent.VC_X;
            case 'Y':
                return NativeKeyEvent.VC_Y;
            case 'Z':
                return NativeKeyEvent.VC_Z;
            default:
                return defaultIndicator;
        }
    }

}
