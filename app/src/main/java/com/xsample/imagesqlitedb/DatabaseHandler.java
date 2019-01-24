package com.xsample.imagesqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, "image.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table images(id integer primary key,img blob not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists images");
    }

    //Insert image
    public Boolean insertImage(String x, Integer i) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            FileInputStream fileInputStream = new FileInputStream(x);
            byte[] imgbyte = new byte[fileInputStream.available()];
            fileInputStream.read(imgbyte);
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", 1);
            contentValues.put("img", imgbyte);
            sqLiteDatabase.insert("images", null, contentValues);
            fileInputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
