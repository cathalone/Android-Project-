import java.net.*;
import java.io.*;

public class ClientTest {
    private final Socket client;
    private final String serverName;
    private final int port;
    private Message data;

    public ClientTest(int port, String serverName, Message data) throws IOException {
        this.serverName = serverName;
        this.client = new Socket(serverName, port);
        this.port = port;
        this.data = data;
    }

    public void startReceivingData() {
        while(true) {
            try {
                DataInputStream in = new DataInputStream(client.getInputStream());
                this.data.setData(in.readUTF());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}