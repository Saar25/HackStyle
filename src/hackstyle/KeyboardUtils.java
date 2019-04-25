package hackstyle;

import org.jnativehook.keyboard.NativeKeyEvent;

public final class KeyboardUtils {

    private KeyboardUtils() {

    }

    public static int parseCharToKey(char c) {
        return parseCharToKey(c, NativeKeyEvent.VC_E);
    }

    public static int parseCharToKey(char c, int defaultIndicator) {
        switch (c) {
            case 'A': return NativeKeyEvent.VC_A;
            case 'B': return NativeKeyEvent.VC_B;
            case 'C': return NativeKeyEvent.VC_C;
            case 'D': return NativeKeyEvent.VC_D;
            case 'E': return NativeKeyEvent.VC_E;
            case 'F': return NativeKeyEvent.VC_F;
            case 'G': return NativeKeyEvent.VC_G;
            case 'H': return NativeKeyEvent.VC_H;
            case 'I': return NativeKeyEvent.VC_I;
            case 'J': return NativeKeyEvent.VC_J;
            case 'K': return NativeKeyEvent.VC_K;
            case 'L': return NativeKeyEvent.VC_L;
            case 'M': return NativeKeyEvent.VC_M;
            case 'N': return NativeKeyEvent.VC_N;
            case 'O': return NativeKeyEvent.VC_O;
            case 'P': return NativeKeyEvent.VC_P;
            case 'Q': return NativeKeyEvent.VC_Q;
            case 'R': return NativeKeyEvent.VC_R;
            case 'S': return NativeKeyEvent.VC_S;
            case 'T': return NativeKeyEvent.VC_T;
            case 'U': return NativeKeyEvent.VC_U;
            case 'V': return NativeKeyEvent.VC_V;
            case 'W': return NativeKeyEvent.VC_W;
            case 'X': return NativeKeyEvent.VC_X;
            case 'Y': return NativeKeyEvent.VC_Y;
            case 'Z': return NativeKeyEvent.VC_Z;
            case '1': return NativeKeyEvent.VC_1;
            case '2': return NativeKeyEvent.VC_2;
            case '3': return NativeKeyEvent.VC_3;
            case '4': return NativeKeyEvent.VC_4;
            case '5': return NativeKeyEvent.VC_5;
            case '6': return NativeKeyEvent.VC_6;
            case '7': return NativeKeyEvent.VC_7;
            case '8': return NativeKeyEvent.VC_8;
            case '9': return NativeKeyEvent.VC_9;
            case '0': return NativeKeyEvent.VC_0;
            default: return defaultIndicator;
        }
    }
}
