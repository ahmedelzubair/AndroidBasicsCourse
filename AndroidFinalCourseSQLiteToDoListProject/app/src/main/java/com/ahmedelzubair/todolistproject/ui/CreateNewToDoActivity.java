package com.ahmedelzubair.todolistproject.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmedelzubair.todolistproject.R;
import com.ahmedelzubair.todolistproject.data.DatabaseHelper;

public class CreateNewToDoActivity extends AppCompatActivity {

    private EditText etTitle, etBody;
    private Button btnCreateToDo;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_to_do);

        databaseHelper = DatabaseHelper.getInstance(this);

        initViews();
        setViewsListeners();
    }

    private void initViews() {
        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        btnCreateToDo = findViewById(R.id.btnCreateToDo);
    }

    private void setViewsListeners() {
        btnCreateToDo.setOnClickListener(view -> {

            String title = etTitle.getText().toString().trim();
            String body = etBody.getText().toString().trim();

            databaseHelper.createNewToDoTask(title,body);
            Toast.makeText(this, "ToDo added!", Toast.LENGTH_SHORT).show();
        });
    }

}