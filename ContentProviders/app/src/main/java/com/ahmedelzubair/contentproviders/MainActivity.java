package com.ahmedelzubair.contentproviders;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickAddDetails(View view) {
        // class to add values in the database
        ContentValues values = new ContentValues();
        // fetching text from user
        values.put(UsersContentProvider.name, ((EditText) findViewById(R.id.textName))
                .getText().toString());
        // inserting into database through content URI
        getContentResolver().insert(UsersContentProvider.CONTENT_URI, values);
        // displaying a toast message
        Toast.makeText(getBaseContext(), "New Record Inserted",
                Toast.LENGTH_LONG).show();
    }

    public void onClickShowDetails(View view) {
        // inserting complete table details in this text field
        TextView resultView = findViewById(R.id.res);
        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(UsersContentProvider.CONTENT_URI,
                null, null, null, null);
        // iteration of the cursor
        // to print whole table
        if (cursor.moveToFirst()) {
            StringBuilder strBuild = new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n" + cursor.getString(cursor.getColumnIndex("id")) +
                        "- " + cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        } else {
            resultView.setText("No Records Found");
        }
    }
}
