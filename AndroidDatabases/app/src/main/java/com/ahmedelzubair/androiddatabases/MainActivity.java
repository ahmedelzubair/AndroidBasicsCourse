package com.ahmedelzubair.androiddatabases;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // SQLite Database in android is a database to store local data
    // its used a lot for caching data for offline usage like facebook

    // CRUD Operations
    // C => CREATE
    // R => READ
    // U => UPDATE
    // D => DELETE

    private Button btnAdd, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setViewsListeners();
    }


    private void initViews() {
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void setViewsListeners() {
        btnAdd.setOnClickListener(view -> {
            startActivity(new Intent(this, AddUserActivity.class));
        });
        btnUpdate.setOnClickListener(view -> {
            startActivity(new Intent(this, UpdateUserActivity.class));
        });
        btnDelete.setOnClickListener(view -> {
            startActivity(new Intent(this, DeleteUserActivity.class));
        });

    }


}