package net.wadelzubair.recyclerviewexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

    }

    private void initRecyclerView() {
        rvData = findViewById(R.id.rvData);
        rvData.setLayoutManager(new GridLayoutManager(this,3));
        rvData.setHasFixedSize(true);

        List<String> list = getReclyerData();

        adapter = new RecyclerAdapter(this,list);
        rvData.setAdapter(adapter);
    }

    private List<String> getReclyerData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("Item : " + i);
        }
        return list;
    }

}