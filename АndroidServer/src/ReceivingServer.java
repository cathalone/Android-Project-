import java.net.*;
import java.io.*;

public class ReceivingServer{
    private final ServerSocket serverSocket;
    private final Socket server;
    private final int port;
    private Message data;

    public ReceivingServer(int port, Message data) throws IOException {
        serverSocket = new ServerSocket(port);
        server = serverSocket.accept();
        this.port = port;
        this.data = data;
    }

    public void startReceivingData() {
            try {
                while(true) {
                    DataInputStream in = new DataInputStream(server.getInputStream());
                    this.data.setData(in.readUTF());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
