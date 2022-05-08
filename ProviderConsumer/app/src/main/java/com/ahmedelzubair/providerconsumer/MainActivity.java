package com.ahmedelzubair.providerconsumer;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // defining content URI
    static final String URI = "content://com.ahmedelzubair.contentproviders/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowDetails(View view) {
        // inserting complete table details in this text field
        TextView tvResult = findViewById(R.id.tvResult);
        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse(URI), null,
                null, null, null);

        // iteration of the cursor
        // to print whole table
        if (cursor.moveToFirst()) {
            StringBuilder strBuild = new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n" + cursor.getString(cursor.getColumnIndex("id")) +
                        "- " + cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            cursor.close();
            tvResult.setText(strBuild);
        } else {
            tvResult.setText("No Records Found");
        }
    }
}