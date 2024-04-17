import java.io.IOException;

public class ClientTask implements Runnable{
    private Message data;
    private final int port;
    private final String serverName;
    public ClientTask(Message data, int port, String serverName) {
        this.serverName = serverName;
        this.port = port;
        this.data = data;
    }
    @Override
    public void run() {
        try {
            Client clientTest = new Client(this.port, this.serverName, this.data);
            clientTest.startReceivingData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
