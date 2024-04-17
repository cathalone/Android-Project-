import java.io.IOException;

public class RespondingServerTask implements Runnable{
    private Message data;
    private final int port;
    public RespondingServerTask(Message data, int port) {
        this.port = port;
        this.data = data;
    }
    @Override
    public void run() {
        try {
            RespondingServer respondingServer = new RespondingServer(this.port, this.data);
            respondingServer.startRespondingData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
