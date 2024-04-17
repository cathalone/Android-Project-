import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RespondingServer{
    private final ServerSocket serverSocket;
    private final Socket server;
    private final int port;
    private Message data;

    public RespondingServer(int port, Message data) throws IOException {
        serverSocket = new ServerSocket(port);
        server = serverSocket.accept();
        this.port = port;
        this.data = data;
    }

    public void startRespondingData() {
        while(true) {
            try {
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(this.data.getData());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
