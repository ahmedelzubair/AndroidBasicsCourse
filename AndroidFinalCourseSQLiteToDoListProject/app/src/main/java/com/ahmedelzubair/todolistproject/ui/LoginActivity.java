package com.ahmedelzubair.todolistproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmedelzubair.todolistproject.R;
import com.ahmedelzubair.todolistproject.data.AppPrefs;
import com.ahmedelzubair.todolistproject.data.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnGoToSignUp;
    private CheckBox cbRememberMe;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = DatabaseHelper.getInstance(this);

        initViews();
        setViewsListeners();
        checkIsUserLoggedIn();

    }

    private void checkIsUserLoggedIn() {
        if (AppPrefs.getInstance(this).isRememberMeChecked()) {
            if (databaseHelper.isUserExists()) {
                if (databaseHelper.getUserData().length > 1) {
                    etUsername.setText(databaseHelper.getUserData()[0]);
                    etPassword.setText(databaseHelper.getUserData()[1]);
                }
            }
        }
    }

    private void initViews() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);

        btnLogin = findViewById(R.id.btnLogin);
        btnGoToSignUp = findViewById(R.id.btnGoToSignUp);
    }

    private void setViewsListeners() {

        btnLogin.setOnClickListener(view -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (!TextUtils.isEmpty(password) &&
                    !TextUtils.isEmpty(username)) {

                if (databaseHelper.isUserLoginDataValid(username, password)) {
                    AppPrefs.getInstance(this).
                            saveRememberMeStatus(cbRememberMe.isChecked());

                    Toast.makeText(this, "Logged in!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Incorrect data!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoToSignUp.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });


    }


}