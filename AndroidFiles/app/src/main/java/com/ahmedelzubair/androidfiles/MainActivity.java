package com.ahmedelzubair.androidfiles;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // android files :
    // its uses the java files API for managing files
    // creating files
    // reading files
    // listing files
    // deleting files

    // there is two storage areas in android
    // external storage (requires permission) link for permission details in desc
    // internal storage
    //


    Button btnWriteData, btnReadData,btnDeleteFiles;
    TextView tvReadData, tvFilesList;
    EditText etDataToWrite;

    String data;
    private String fileName = "dataFile2.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setViewsListener();
        loadFilesList();

    }

    private void loadFilesList() {

        List<String> files = FileUtils.getFilesList(getFilesDir());

        StringBuilder builder = new StringBuilder();
        for (String filePath : files) {
            builder.append(filePath);
        }

        tvFilesList.setText(builder.toString());

    }

    private void initViews() {
        btnWriteData = findViewById(R.id.btnWriteData);
        btnReadData = findViewById(R.id.btnReadData);
        btnDeleteFiles = findViewById(R.id.btnDeleteFiles);
        tvFilesList = findViewById(R.id.tvFilesList);

        etDataToWrite = findViewById(R.id.etData);
        tvReadData = findViewById(R.id.textView2);
    }

    private void setViewsListener() {
        btnWriteData.setOnClickListener(v -> {
            data = etDataToWrite.getText().toString();
            try {
                FileOutputStream fOut = openFileOutput(fileName, MODE_PRIVATE);
                fOut.write(data.getBytes());
                fOut.close();
                Toast.makeText(getBaseContext(), "file saved", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        btnReadData.setOnClickListener(v -> {
            try {
                FileInputStream fin = openFileInput(fileName);
                int character;
                StringBuilder temp = new StringBuilder();
                while ((character = fin.read()) != -1) {
                    temp.append((char) character);
                }
                tvReadData.setText(temp.toString());
                Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnDeleteFiles.setOnClickListener(v -> {
          FileUtils.deleteFileInDir(getFilesDir());
        });
    }


}