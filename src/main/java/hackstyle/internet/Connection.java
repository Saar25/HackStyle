package hackstyle.internet;

public class Connection {

    private final String ip;
    private final int port;

    public Connection(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Connection)) {
            return false;
        }
        Connection c = (Connection) obj;
        return ip.equals(c.ip) && port == c.port;
    }

    @Override
    public String toString() {
        return "{Ip: " + ip + " - Port: " + port + "}";
    }

}
