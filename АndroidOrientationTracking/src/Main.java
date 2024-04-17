import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Message dataFromDevice = new Message();
        int[] parsedData = new int[3];
        Point[] points = new Point[8];
        for (int i = 0; i < 8; i++){
            points[i] = new Point(0,0,0);
        }
        Point[] pointsForAnimation = new Point[8];
        for (int i = 0; i < 8; i++){
            pointsForAnimation[i] = new Point(0,0,0);
        }
        Point[] points_default= new Point[]
            {
                new Point(-0.2f,-1,-2),new Point(-0.2f,1,-2),
                new Point(-0.2f,1,2), new Point(-0.2f,-1,2),
                new Point(0.2f,-1,-2),new Point(0.2f,1,-2),
                new Point(0.2f,1,2), new Point(0.2f,-1,2)
            };

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Animated Point");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(new AnimatedPoint(pointsForAnimation), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        ClientTask clientTask = new ClientTask(dataFromDevice, 6067, "localhost");

        Thread clientThread = new Thread(clientTask);

        clientThread.start();

        while (true) {
            DataProcessing.parseData(dataFromDevice.getData(), parsedData);
            Rotation.pointsRotationWithMemory(points, points_default, (float) Math.toRadians(parsedData[2]), (float) Math.toRadians(parsedData[1]), (float) Math.toRadians(parsedData[0]));
//            Rotation.pointsRotation(points_default,0.000001f, 0.000001f, 0.000001f);
            for (int i = 0; i < pointsForAnimation.length; i++) {
                pointsForAnimation[i].setX(points[i].getX());
                pointsForAnimation[i].setY(points[i].getY());
                pointsForAnimation[i].setZ(points[i].getZ());
            }
        }
    }
}