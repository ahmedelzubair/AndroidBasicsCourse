package com.ahmedelzubair.todolistproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelzubair.todolistproject.R;
import com.ahmedelzubair.todolistproject.adapters.ToDoListAdapter;
import com.ahmedelzubair.todolistproject.adapters.UpdateListener;
import com.ahmedelzubair.todolistproject.data.DatabaseHelper;

public class ToDoListActivity extends AppCompatActivity implements UpdateListener {

    private Button btnCreateNewToDo;
    private RecyclerView rvToDoList;
    private DatabaseHelper databaseHelper;
    private ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_acivity);

        databaseHelper = DatabaseHelper.getInstance(this);

        initViews();
        setViewsListeners();
        loadToDoTasks();
    }

    private void loadToDoTasks() {
        adapter = new ToDoListAdapter(this, databaseHelper.getAllTasks(), this);
        rvToDoList.setHasFixedSize(true);
        rvToDoList.setLayoutManager(new LinearLayoutManager(this));
        rvToDoList.setAdapter(adapter);
    }

    private void initViews() {
        rvToDoList = findViewById(R.id.rvToDoList);
        btnCreateNewToDo = findViewById(R.id.btnCreateNewToDo);
    }

    private void setViewsListeners() {
        btnCreateNewToDo.setOnClickListener(view -> {
            startActivity(new Intent(this, CreateNewToDoActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadToDoTasks();
    }

    @Override
    public void onUpdateList() {
        loadToDoTasks();
    }
}