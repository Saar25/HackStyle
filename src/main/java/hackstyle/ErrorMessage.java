package hackstyle;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ErrorMessage {

    private static final DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private ErrorMessage() {

    }

    public static void createErrorFile(Exception exception) {
        try (final PrintWriter writer = new PrintWriter("HackStyleError.txt")) {
            writer.println("Date: " + format.format(new Date()));
            writer.println("*******\nAn error occurred, please send me (Style) the error so I can fix it :}\n*******\n");

            writer.println(exception.getClass().getName() + ": " + exception.getMessage());
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                writer.println(stackTraceElement.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        exception.printStackTrace();
    }

}
