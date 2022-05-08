package com.ahmedelzubair.androidsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etPassword, etUsername;
    Button btnLogin;
    CheckBox checkboxRememberMe;

    public static final String MyPREFERENCES = "my_prefs";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        initViews();
        setViewsListeners();
    }

    private void initViews() {
        etPassword = (EditText) findViewById(R.id.etPassword);
        etUsername = (EditText) findViewById(R.id.etUsername);
        checkboxRememberMe = findViewById(R.id.checkboxRememberMe);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        if (sharedpreferences.getBoolean("remember_me", false)) {
            etUsername.setText(sharedpreferences.getString("username", ""));
            etPassword.setText(sharedpreferences.getString("password", ""));
        }
    }

    private void setViewsListeners() {
        btnLogin.setOnClickListener(v -> {
            if (checkboxRememberMe.isChecked()) {
                String pass = etPassword.getText().toString();
                String username = etUsername.getText().toString();

                editor.putString("username", username);
                editor.putString("password", pass);
                editor.putBoolean("remember_me", true);
                editor.apply();
                Toast.makeText(MainActivity.this, "Your data Saved", Toast.LENGTH_LONG).show();
            }
        });
    }
}