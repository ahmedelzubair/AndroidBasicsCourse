package com.ahmedelzubair.androidfiles;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {


    public static final String TAG = FileUtils.class.getSimpleName();

    public static void deleteFileInDir(File folder) {
        File[] files = folder.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f != null && f.isDirectory()) {
                    deleteFileInDir(f);
                } else {
                    deleteFile(f);
                }
            }
        }
    }

    public static void deleteFile(File file) {
        if (file != null && file.exists()) {
            boolean deleted = file.delete();
            Log.i(TAG, ": deleted = " + deleted);
        }
    }

    public static List<String> getFilesList(File fileAtPath) {
        ArrayList<String> filesList = new ArrayList<>();
        File[] files = fileAtPath.listFiles();
        for (File file : files) {
            filesList.add(file.getAbsolutePath());
            filesList.add("\n");
        }
        return filesList;
    }




}
