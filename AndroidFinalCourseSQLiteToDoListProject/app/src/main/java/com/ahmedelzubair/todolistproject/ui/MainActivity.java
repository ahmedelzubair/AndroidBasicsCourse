package com.ahmedelzubair.todolistproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmedelzubair.todolistproject.R;
import com.ahmedelzubair.todolistproject.data.AppPrefs;
import com.ahmedelzubair.todolistproject.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etUsername, etPassword;
    private Button btnSignUp, btnGoToLogin;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = DatabaseHelper.getInstance(this);

        initViews();
        setViewsListeners();
        checkToDoListUser();
    }

    private void checkToDoListUser() {
        if (databaseHelper.isUserExists()){
            startActivity(new Intent(this,ToDoListActivity.class));
            finish();
        }
    }


    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnGoToLogin = findViewById(R.id.btnGoToLogin);
    }

    private void setViewsListeners() {

        btnSignUp.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (!TextUtils.isEmpty(email) &&
                    !TextUtils.isEmpty(password) &&
                    !TextUtils.isEmpty(username)) {

                databaseHelper.createNewUser(username, email, password);
                Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoToLogin.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

    }


}