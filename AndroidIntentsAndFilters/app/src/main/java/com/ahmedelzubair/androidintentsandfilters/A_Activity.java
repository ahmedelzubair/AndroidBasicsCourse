package com.ahmedelzubair.androidintentsandfilters;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class A_Activity extends AppCompatActivity {

    TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aactivity);

        String username = getIntent().getExtras().getString("username");
        tvUsername = findViewById(R.id.tvUsername);
        tvUsername.setText(username);


    }


}