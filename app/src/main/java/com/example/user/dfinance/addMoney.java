package com.example.user.dfinance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class addMoney extends AppCompatActivity implements View.OnClickListener {

    Button bAM1;
    Button bAM2;
    Button bAM3;
    TextView tvAM1;
    TextView tvAM2;
    Spinner spAM1;
    EditText etAM1;
    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        bAM1 = (Button) findViewById(R.id.bAM1);
        bAM2 = (Button) findViewById(R.id.bAM2);
        bAM3 = (Button) findViewById(R.id.bAM3);
        bAM1.setOnClickListener(this);
        bAM2.setOnClickListener(this);
        bAM3.setOnClickListener(this);
        tvAM1 = (TextView) findViewById(R.id.tvAM1);
        tvAM2 = (TextView) findViewById(R.id.tvAM2);
        spAM1 = (Spinner) findViewById(R.id.spAM1);
        etAM1 = (EditText) findViewById(R.id.etAM1);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        String selected = spAM1.getSelectedItem().toString();
        String profit =  selected.toString();
        String category =  etAM1.getText().toString();
        String id = etAM1.getText().toString();


        String sobaka = selected.toString();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.bAM1:
                contentValues.put(DBHelper.KEY_PROFIT, profit);
                contentValues.put(DBHelper.KEY_CATEGORY, category);
                contentValues.put(DBHelper.KEY_YEAR, year);
                contentValues.put(DBHelper.KEY_MONTH, month);
                contentValues.put(DBHelper.KEY_DAY, day);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;
            case R.id.bAM2:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
                if (cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int profitIndex = cursor.getColumnIndex(DBHelper.KEY_PROFIT);
                    int categoryIndex = cursor.getColumnIndex(DBHelper.KEY_CATEGORY);
                    int yearIndex = cursor.getColumnIndex(DBHelper.KEY_YEAR);
                    int monthIndex = cursor.getColumnIndex(DBHelper.KEY_MONTH);
                    int dayIndex = cursor.getColumnIndex(DBHelper.KEY_DAY);
                    do {
                        Log.d("mLog1", "id = " + cursor.getInt(idIndex) +
                                ", profit = " + cursor.getString(profitIndex) +
                                ", category = " + cursor.getString(categoryIndex) +
                                ", year = " + cursor.getString(yearIndex) +
                                ", month = " + cursor.getString(monthIndex) +
                                ", day = " + cursor.getString(dayIndex)
                        );
                    }while (cursor.moveToNext());
                } else Log.d("mLog","0 rows");
                cursor.close();
                break;

            case R.id.bAM3:
                if (id.equalsIgnoreCase(""))
                {
                    break;
                }
                int delCount = database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "=" + id, null);
                Log.d("mLog", "deleted rows count = " + delCount);


        }
        dbHelper.close();
    }

}

