package com.ahmedelzubair.androiddatabases;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("Range")
public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "app_db.db";
    public static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper db;
    private final SQLiteDatabase database;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (db == null) {
            db = new DatabaseHelper(context);
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUsers(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void createTableUsers(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", first_name TEXT NOT NULL" +
                ", last_name TEXT NOT NULL" +
                ", password TEXT NOT NULL )");
    }


    public void insertUser(String first_name, String last_name, String password) {
        ContentValues values = new ContentValues();

        values.put("first_name", first_name);
        values.put("last_name", last_name);
        values.put("password", password);
        database.insert("users", null, values);
    }


    public void updateUser(String id, String first_name, String last_name, String password) {
        ContentValues values = new ContentValues();

        values.put("first_name", first_name);
        values.put("last_name", last_name);
        values.put("password", password);
        database.update("users", values, " id = " + id, null);
    }

    public void deleteUser(String id) {
        database.delete("users", " id = " + id, null);
    }


    public String getAllUsers() {
        StringBuilder stringBuilder = new StringBuilder();
        String query = "SELECT * FROM users ";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                stringBuilder.append("ID : ").append(cursor.getString(cursor.getColumnIndex("id")));
                stringBuilder.append("\n");
                stringBuilder.append("First name : ").append(cursor.getString(cursor.getColumnIndex("first_name")));
                stringBuilder.append("\n");
                stringBuilder.append("Last name : ").append(cursor.getString(cursor.getColumnIndex("last_name")));
                stringBuilder.append("\n");
                stringBuilder.append("Password : ").append(cursor.getString(cursor.getColumnIndex("password")));
                stringBuilder.append("\n");
            } while (cursor.moveToNext());
        }
        // close db connection
        cursor.close();
        return stringBuilder.toString();
    }


}
