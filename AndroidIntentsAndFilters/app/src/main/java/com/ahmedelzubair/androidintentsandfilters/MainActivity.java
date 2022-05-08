package com.ahmedelzubair.androidintentsandfilters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    // An Android Intent:
    // is an abstract description of an operation to be performed.

    // Here is an examples of intent usages:
    // open activity inside android app & ( sending data between activities )
    // start android services
    // send android broadcast receivers
    public void onButtonClicked(View view) {

        Utils.shareApp(this);

//        Intent intent = new Intent(this, A_Activity.class);
//        intent.putExtra("username","omer");
//        startActivity(intent);

    }

}