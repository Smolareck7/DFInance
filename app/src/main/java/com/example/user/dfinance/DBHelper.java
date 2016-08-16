package com.example.user.dfinance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_PROFIT = "profit";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_YEAR = "year";
    public static final String KEY_MONTH = "month";
    public static final String KEY_DAY = "day";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key,"
                + KEY_PROFIT + " text,"
                + KEY_CATEGORY + " text,"
                + KEY_YEAR + " text,"
                + KEY_MONTH + " text,"
                + KEY_DAY + " text"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);
    }
}