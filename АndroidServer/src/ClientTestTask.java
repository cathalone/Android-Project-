import java.io.IOException;

public class ClientTestTask implements Runnable{
    private Message data;
    private final int port;
    private final String serverName;
    public ClientTestTask(Message data, int port, String serverName) {
        this.serverName = serverName;
        this.port = port;
        this.data = data;
    }
    @Override
    public void run() {
        try {
            ClientTest clientTest = new ClientTest(this.port, this.serverName, this.data);
            clientTest.startReceivingData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
