package com.example.user.dfinance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
    TextView tvAM3;
    Spinner spAM1;
    EditText etAM1;
    EditText etAM2;
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
        tvAM3 = (TextView) findViewById(R.id.tvAM3);
        spAM1 = (Spinner) findViewById(R.id.spAM1);
        etAM1 = (EditText) findViewById(R.id.etAM1);
        etAM2 = (EditText) findViewById(R.id.etAM2);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        String selected = spAM1.getSelectedItem().toString();
        String profit =  etAM1.getText().toString();
        int profit2 = Integer.parseInt(profit);
        String category =  selected.toString();
        String id = etAM1.getText().toString();
        String title = etAM2.getText().toString();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int summa = 0;
        String summa1 = null;
        int i = 0;

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues contentValues1 = new ContentValues();



        switch (v.getId()) {
            case R.id.bAM1:
                contentValues.put(DBHelper.KEY_PROFIT, profit2);
                contentValues.put(DBHelper.KEY_CATEGORY, category);
                contentValues.put(DBHelper.KEY_TITLE, title);
                contentValues.put(DBHelper.KEY_YEAR, year);
                contentValues.put(DBHelper.KEY_MONTH, month);
                contentValues.put(DBHelper.KEY_DAY, day);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);


                break;
            case R.id.bAM2:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
                Cursor cursor1 = database.query(DBHelper.TABLE_SPIN, null, null, null, null, null, null);

                if (cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int profitIndex = cursor.getColumnIndex(DBHelper.KEY_PROFIT);
                    int categoryIndex = cursor.getColumnIndex(DBHelper.KEY_CATEGORY);
                    int titleIndex = cursor.getColumnIndex(DBHelper.KEY_TITLE);
                    int yearIndex = cursor.getColumnIndex(DBHelper.KEY_YEAR);
                    int monthIndex = cursor.getColumnIndex(DBHelper.KEY_MONTH);
                    int dayIndex = cursor.getColumnIndex(DBHelper.KEY_DAY);

                    do {
                        Log.d("mLog1", "id = " + cursor.getInt(idIndex) +
                                ", profit = " + cursor.getInt(profitIndex) +
                                ", category = " + cursor.getString(categoryIndex) +
                                ", title = " + cursor.getString(titleIndex) +
                                ", year = " + cursor.getString(yearIndex) +
                                ", month = " + cursor.getString(monthIndex) +
                                ", day = " + cursor.getString(dayIndex)
                        );
                            summa += cursor.getInt(profitIndex);

                    }while (cursor.moveToNext());
                } else Log.d("mLog","0 rows");
                cursor.close();

                summa1 = Integer.toString(summa);
                tvAM3.setText(summa1);

                if (cursor1.moveToFirst()){
                    int idIndex1 = cursor1.getColumnIndex(DBHelper.KEY_IDD);
                    int statIndex1 = cursor1.getColumnIndex(DBHelper.KEY_STAT);
                    int spinnerIndex1 = cursor1.getColumnIndex(DBHelper.KEY_SPINNER);
                    do {
                        Log.d("mLog2", "id = " + cursor1.getInt(idIndex1) +
                                ", stat = " + cursor1.getString(statIndex1) +
                                ", spinner = " + cursor1.getString(spinnerIndex1)
                        );

                    }while (cursor1.moveToNext());
                } else Log.d("mLog2","0 rows");
                cursor1.close();


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

