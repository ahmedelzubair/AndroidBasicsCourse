package com.ahmedelzubair.androiddatabases;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity {

    private EditText edtFirstName, edtLastName, edtPassword;
    private Button btnAdd;
    private TextView tvUsers;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        databaseHelper = DatabaseHelper.getInstance(this);

        initViews();
        setViewsListeners();
        loadUsers();
    }


    private void initViews() {
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtPassword = findViewById(R.id.edtPassword);
        btnAdd = findViewById(R.id.btnAdd);
        tvUsers = findViewById(R.id.tvUsers);

    }

    private void setViewsListeners() {

        btnAdd.setOnClickListener(view -> {
            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();
            String password = edtPassword.getText().toString();

            databaseHelper.insertUser(firstName, lastName, password);
            loadUsers();
        });

    }

    private void loadUsers() {
        tvUsers.setText(databaseHelper.getAllUsers());
    }


}