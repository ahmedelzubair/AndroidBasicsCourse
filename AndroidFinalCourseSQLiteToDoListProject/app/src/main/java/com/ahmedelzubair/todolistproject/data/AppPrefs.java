package com.ahmedelzubair.todolistproject.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static AppPrefs instance;
    private static String TAG = AppPrefs.class.getSimpleName();

    public static AppPrefs getInstance(Context context) {
        if (instance == null) {
            instance = new AppPrefs(context);
        }
        return instance;
    }

    private AppPrefs(Context context) {
        preferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveRememberMeStatus(boolean isRememberMeChecked) {
        editor.putBoolean("isRememberMeChecked", isRememberMeChecked).apply();
    }

    public boolean isRememberMeChecked() {
        return preferences.getBoolean("isRememberMeChecked", false);
    }


}
