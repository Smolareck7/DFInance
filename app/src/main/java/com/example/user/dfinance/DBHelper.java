package com.example.user.dfinance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 26;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_SPIN = "spin";

    public static final String KEY_ID = "_id";
    public static final String KEY_IDD = "_id";
    public static final String KEY_PROFIT = "profit";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_STATUS = "status";
    public static final String KEY_TITLE = "title";
    public static final String KEY_YEAR = "year";
    public static final String KEY_MONTH = "month";
    public static final String KEY_DAY = "day";

    public static final String KEY_STAT = "stat";
    public static final String KEY_SPINNER = "spinner";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "
                + TABLE_SPIN + "("
                + KEY_IDD + " integer primary key autoincrement,"
                + KEY_STAT + " text," // Доход или расход
                + KEY_SPINNER + " text" // Название категории
                + ")");

        db.execSQL("create table "
                + TABLE_CONTACTS + "("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_PROFIT + " text," // Деньги
                + KEY_STATUS + " text," // Статус
                + KEY_CATEGORY + " text," // Категория
                + KEY_TITLE + " text,"
                + KEY_YEAR + " text,"
                + KEY_MONTH + " text,"
                + KEY_DAY + " text"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);
        db.execSQL("drop table if exists " + TABLE_SPIN);

        onCreate(db);
    }
}