package hackstyle.internet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Netstat {

    private final List<Connection> connections;

    private Netstat(List<Connection> connections) {
        this.connections = connections;
    }

    public static Netstat current() {
        return new Netstat(getAllConnections());
    }

    private static List<Connection> getAllConnections() {
        String netstat = getNetstat();
        List<String> stringConnections = Arrays.stream(netstat.split("\\r\\n"))
                .filter(s -> s.contains("TCP"))
                .map(s -> s.split("\\s+")[3])
                .collect(Collectors.toList());
        List<Connection> connections = new ArrayList<>();
        for (String stringConnection : stringConnections) {
            final String[] parts = stringConnection.split(":");
            final int port = Integer.parseInt(parts[1]);
            final String ip = parts[0];
            connections.add(new Connection(ip, port));
        }
        return connections;
    }

    private static String getNetstat() {
        String netstat = "";
        try {
            final Process p = Runtime.getRuntime().exec("netstat -n");
            final StringBuilder builder = new StringBuilder();

            int read;
            byte[] bytes = new byte[300];
            while ((read = p.getInputStream().read(bytes)) != -1) {
                for (int i = 0; i < read; i++) {
                    builder.append((char) bytes[i]);
                }
            }
            p.destroy();
            netstat = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return netstat;
    }

    private static <T> List<T> getDifference(List<T> l1, List<T> l2) {
        List<T> difference = new ArrayList<>(l1);
        difference.removeAll(l2);
        return difference;
    }

    public void setCurrent() {
        connections.clear();
        connections.addAll(getAllConnections());
    }

    public List<Connection> differenceFrom(Netstat other) {
        return getDifference(connections, other.connections);
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
