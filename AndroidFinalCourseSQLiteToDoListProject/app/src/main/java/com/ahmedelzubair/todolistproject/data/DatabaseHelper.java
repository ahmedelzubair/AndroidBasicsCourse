package com.ahmedelzubair.todolistproject.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ahmedelzubair.todolistproject.domain.ToDoItem;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Range")
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String TAG = DatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "to_do_list_db";
    private final SQLiteDatabase db;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        createUsersTable(sqLiteDatabase);
        createToDoTasksTable(sqLiteDatabase);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void createUsersTable(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", username TEXT NOT NULL" +
                ", email TEXT NOT NULL" +
                ", password TEXT NOT NULL" +
                ")");

    }

    private void createToDoTasksTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE to_do_list (id INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", title TEXT NOT NULL" +
                ", body TEXT NOT NULL" +
                ", status INTEGER NOT NULL" +
                ")");
    }

    public void createNewToDoTask(String title, String body) {
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("body", body);
        values.put("status", 0);

        db.insert("to_do_list", null, values);
    }


    public void updateToDoTask(int id, String title, String body) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("body", body);
        db.update("to_do_list", values, " id = " + id, null);
    }

    public void updateToDoTaskStatus(int id, int status) {
        ContentValues values = new ContentValues();
        values.put("status", status);
        db.update("to_do_list", values, " id = " + id, null);
    }


    public void deleteToDoTask(int id) {
        db.delete("to_do_list", " id = " + id, null);
    }


    public List<ToDoItem> getAllTasks() {
        List<ToDoItem> toDoItems = new ArrayList<>();
        String query = "SELECT * FROM to_do_list ;";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ToDoItem item = new ToDoItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("id")));
                item.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
                item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                item.setBody(cursor.getString(cursor.getColumnIndex("body")));
                toDoItems.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return toDoItems;
    }

    ///////////////////////////////USERS////////////////////////////

    public void createNewUser(String username, String email, String password) {
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("email", email);
        values.put("password", password);

        db.insert("users", null, values);
    }


    public boolean isUserLoginDataValid(String username, String password) {
        String query = "SELECT * FROM users WHERE" +
                " username = '" + username + "' " +
                " AND password = '" + password + "'";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean isUserExists() {
        String query = "SELECT * FROM users ";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public String[] getUserData() {
        String[] userData = new String[2];
        String query = "SELECT * FROM users ";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            userData[0] = cursor.getString(cursor.getColumnIndex("username"));
            userData[1] = cursor.getString(cursor.getColumnIndex("password"));
        }
        cursor.close();
        return userData;
    }

}
