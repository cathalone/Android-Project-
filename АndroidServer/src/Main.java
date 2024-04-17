import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Message dataFromDevise = new Message();
//        Message dataOnClient = new Message();

        ReceivingServerTask receivingServerTask = new ReceivingServerTask(dataFromDevise, 6066);
        RespondingServerTask respondingServerTask = new RespondingServerTask(dataFromDevise, 6067);
//        ClientTestTask clientTestTask = new ClientTestTask(dataOnClient, 6067, "localhost");

        Thread resivingServerThread = new Thread(receivingServerTask);
        Thread respondingServerThread = new Thread(respondingServerTask);
//        Thread clientTestThread = new Thread(clientTestTask);

        resivingServerThread.start();
        respondingServerThread.start();
//        clientTestThread.start();

//        int[] parsedData = new int[] {1,1,1};
//        DataProcessing.printArray(parsedData);
//        while (true) {
//            DataProcessing.parseData(dataOnClient.getData(), parsedData);
//            DataProcessing.printArray(parsedData);
//            Thread.sleep(100);
//        }
    }
}