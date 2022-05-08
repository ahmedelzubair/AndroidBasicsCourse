package com.ahmedelzubair.androiddatabases;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteUserActivity extends AppCompatActivity {

    private EditText edtUserId;
    private Button btnDelete;
    private TextView tvUsers;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        databaseHelper = DatabaseHelper.getInstance(this);

        initViews();
        setViewsListeners();
        loadUsers();
    }


    private void initViews() {
        edtUserId = findViewById(R.id.edtUserId);
        btnDelete = findViewById(R.id.btnDelete);
        tvUsers = findViewById(R.id.tvUsers);

    }

    private void setViewsListeners() {

        btnDelete.setOnClickListener(view -> {
            String userId = edtUserId.getText().toString();
            databaseHelper.deleteUser(userId);
            loadUsers();
        });

    }

    private void loadUsers() {
        tvUsers.setText(databaseHelper.getAllUsers());
    }


}