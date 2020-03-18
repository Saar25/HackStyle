package hackstyle;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ErrorMessage {

    private ErrorMessage() {

    }

    public static void createErrorFile(Exception e) {
        try (PrintWriter writer = new PrintWriter("HackStyleError.txt")) {
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            writer.println("Date: " + format.format(new Date()));
            writer.println("*******\nAn error occurred, please show me (Style) the error so I can fix it :}\n*******\n");

            writer.println(e.getMessage());
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                writer.println(stackTraceElement.toString());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();
    }

}
