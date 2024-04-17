import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimatedPoint extends JPanel implements ActionListener {
    private Point[] points;
    private int width;
    private int height;
    private Timer timer;

    public AnimatedPoint(Point[] points) {
        this.points = points;
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (int i = 0; i < 3; i++) {
            g.drawLine((width/2) + ((int) (points[i].getY() * 100)), (height/2) - ((int) (points[i].getZ() * 100)), (width/2) + ((int) (points[i+1].getY() * 100)), (height/2) - ((int) (points[i+1].getZ() * 100)));
        }
        for (int i = 4; i < 7; i++) {
            g.drawLine((width/2) + ((int) (points[i].getY() * 100)), (height/2) - ((int) (points[i].getZ() * 100)), (width/2) + ((int) (points[i+1].getY() * 100)), (height/2) - ((int) (points[i+1].getZ() * 100)));
        }
        g.drawLine((width/2) + ((int) (points[0].getY() * 100)), (height/2) - ((int) (points[0].getZ() * 100)), (width/2) + ((int) (points[3].getY() * 100)), (height/2) - ((int) (points[3].getZ() * 100)));
        g.drawLine((width/2) + ((int) (points[4].getY() * 100)), (height/2) - ((int) (points[4].getZ() * 100)), (width/2) + ((int) (points[7].getY() * 100)), (height/2) - ((int) (points[7].getZ() * 100)));

        for (int i = 0; i < 4; i++) {
            g.drawLine((width/2) + ((int) (points[i].getY() * 100)), (height/2) - ((int) (points[i].getZ() * 100)), (width/2) + ((int) (points[i+4].getY() * 100)), (height/2) - ((int) (points[i+4].getZ() * 100)));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        width = 1080;
        height = 720;
        return new Dimension(width, height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

