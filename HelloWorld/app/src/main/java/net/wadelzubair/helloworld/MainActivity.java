package net.wadelzubair.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd,btnRemove;
    private TextView tvMyText;
    private CheckBox checkbox;
    private MyFragment fragment = new MyFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        tvMyText = findViewById(R.id.tvMyText);
        checkbox = findViewById(R.id.checkbox);

        btnAdd.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            checkbox.setChecked(true);
        });
        btnRemove.setOnClickListener(view -> {
            tvMyText.setText("btn remove clicked");
        });

        getSupportFragmentManager().beginTransaction().
                add(R.id.llFragments,fragment).commit();


    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(this,"onStop",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(this,"onDestroy",Toast.LENGTH_LONG).show();
//    }

}