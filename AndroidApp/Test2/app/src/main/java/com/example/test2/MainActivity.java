package com.example.test2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private Point[] points_default= new Point[]
    {
            new Point(-0.2f,-1,-2),new Point(-0.2f,1,-2),
            new Point(-0.2f,1,2), new Point(-0.2f,-1,2),
            new Point(0.2f,-1,-2),new Point(0.2f,1,-2),
            new Point(0.2f,1,2), new Point(0.2f,-1,2),

            new Point(-0.2f,1,0), new Point(-0.2f,-1,0),
            new Point(-0.2f,0,2), new Point(-0.2f,0,-2),
            new Point(0.2f,1,0), new Point(0.2f,-1,0),
            new Point(0.2f,0,2), new Point(0.2f,0,-2),

            new Point(-0.2f,1,-1), new Point(-0.2f,-1,-1),
            new Point(-0.2f,1,1), new Point(-0.2f,-1,1),
            new Point(0.2f,1,-1), new Point(0.2f,-1,-1),
            new Point(0.2f,1,1), new Point(0.2f,-1,1),

    };
    private Point[] points = new Point[24];
    private SensorManager sm;
    private Sensor s;
    private TextView tv;
    private TextView[] ps;
    private TextView tIp;
    private SensorEventListener sv;
    private ConstraintLayout.LayoutParams[] layoutParams;
    private StringBuilder stB = new StringBuilder();
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutParams = new ConstraintLayout.LayoutParams[24];
        for (int i = 0; i < 24; i++){
            points[i] = new Point(0,0,0);
        }
        for (int i = 0; i < 24; i++){
            layoutParams[i] = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
        }
        tv = findViewById(R.id.tv);
        tIp = findViewById(R.id.t_ip);
        ps = new TextView[] {findViewById(R.id.P1),findViewById(R.id.P2),
                findViewById(R.id.P3),findViewById(R.id.P4),
                findViewById(R.id.P5),findViewById(R.id.P6),
                findViewById(R.id.P7),findViewById(R.id.P8),
                findViewById(R.id.P9),findViewById(R.id.P10),
                findViewById(R.id.P11),findViewById(R.id.P12),
                findViewById(R.id.P13),findViewById(R.id.P14),
                findViewById(R.id.P15),findViewById(R.id.P16),
                findViewById(R.id.P17),findViewById(R.id.P18),
                findViewById(R.id.P19),findViewById(R.id.P20),
                findViewById(R.id.P21),findViewById(R.id.P22),
                findViewById(R.id.P23),findViewById(R.id.P24)};
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            ip = arguments.get("ip").toString();
            tIp.setText(ip);
        }
        Client sendData = new Client(ip, 6066);
        Thread thread = new Thread(sendData);
        thread.start();

        sv = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, remappedRotationMatrix);

                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix, orientations);

                for (int i = 0; i < 24; i++) {
                    points[i].setX(points_default[i].getX());
                    points[i].setY(points_default[i].getY());
                    points[i].setZ(points_default[i].getZ());
                }

                Rotation.pointsRotation(points, (float)(orientations[2]),
                                 (float)(orientations[1]), (float)(orientations[0]));

                for (int i = 0; i < 24; i++) {
                    layoutParams[i].setMargins(530 + (int)(points[i].getY()*100), 500 - (int)(points[i].getZ()*100), 0, 0);
                    layoutParams[i].leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                    layoutParams[i].topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                    ps[i].setLayoutParams(layoutParams[i]);
                }

                stB.append("x: ");
                stB.append((int)(Math.toDegrees(orientations[0])));
                stB.append(" y: ");
                stB.append((int)(Math.toDegrees(orientations[1])));
                stB.append(" z: ");
                stB.append((int)(Math.toDegrees(orientations[2])));
                tv.setText(stB);
                sendData.setData((int)(Math.toDegrees(orientations[0])) + ","
                        + (int)(Math.toDegrees(orientations[1])) + ","
                        + (int)(Math.toDegrees(orientations[2])));
                stB.setLength(0);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(sv,s,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(sv);
    }
}