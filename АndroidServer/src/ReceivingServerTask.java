import java.io.IOException;

public class ReceivingServerTask implements Runnable{
    private Message data;
    private final int port;
    public ReceivingServerTask(Message data, int port) {
        this.port = port;
        this.data = data;
    }
    @Override
    public void run() {
        try {
            ReceivingServer receivingServer = new ReceivingServer(this.port, this.data);
            receivingServer.startReceivingData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
