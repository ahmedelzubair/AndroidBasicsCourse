package net.wadelzubair.androidservices;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.startButton);
        Button stop = findViewById(R.id.stopButton);

        start.setOnClickListener(view -> {
            startService(new Intent(this, NewService.class));
        });

        stop.setOnClickListener(view -> {
            stopService(new Intent(this, NewService.class));
        });
    }

}
