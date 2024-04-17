package com.example.test2;

import java.net.*;
import java.io.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private Button connectButton;
    private View.OnClickListener oclConnectButton;
    private EditText editIp;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        connectButton = findViewById(R.id.b_connect);
        editIp = findViewById(R.id.ipAddress);
        
        oclConnectButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = editIp.getText().toString();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("ip", ip);
                startActivity(intent);
            }
        };
        connectButton.setOnClickListener(oclConnectButton);
    }
}