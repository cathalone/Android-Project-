package com.example.test2;

import java.net.*;
import java.io.*;

public class Client implements Runnable {
    private final String serverName;
    private final int port;
    private String data;
    public Client(String serverName, int port) {
        this.port = port;
        this.serverName = serverName;
        data = "";
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        Socket client = null;
        try {
            client = new Socket(serverName, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            while (true) {
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);
                out.writeUTF(this.data);
                Thread.sleep(30);
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}