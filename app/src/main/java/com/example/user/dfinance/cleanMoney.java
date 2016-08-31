package com.example.user.dfinance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class cleanMoney extends AppCompatActivity implements View.OnClickListener {

    Button bCM1;
    Button bCM2;
    Button bCM3;
    TextView tvCM1;
    TextView tvCM2;
    TextView tvCM3;
    Spinner spCM1;
    Spinner spCM2;
    Spinner spCM3;
    EditText etCM1;
    EditText etCM2;
    GridView gvCM1;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_money);

        bCM1 = (Button) findViewById(R.id.bCM1);
        bCM2 = (Button) findViewById(R.id.bCM2);
        bCM3 = (Button) findViewById(R.id.bCM3);
        bCM1.setOnClickListener(this);
        bCM2.setOnClickListener(this);
        bCM3.setOnClickListener(this);
        tvCM1 = (TextView) findViewById(R.id.tvCM1);
        tvCM2 = (TextView) findViewById(R.id.tvCM2);
        tvCM3 = (TextView) findViewById(R.id.tvCM3);
        spCM1 = (Spinner) findViewById(R.id.spCM1);
        spCM2 = (Spinner) findViewById(R.id.spCM2);
        spCM3 = (Spinner) findViewById(R.id.spCM3);
        etCM1 = (EditText) findViewById(R.id.etCM1);
        etCM2 = (EditText) findViewById(R.id.etCM2);
        gvCM1 = (GridView) findViewById(R.id.gvCM1);

        dbHelper = new DBHelper(this);
        SQLiteDatabase database2 = dbHelper.getWritableDatabase();

        String  c = null;
        String[] d = null;
        c = "stat == ?";
        d = new String[]{"Расход"};

        Cursor cursor2 = database2.query(DBHelper.TABLE_SPIN, null, c, d, null, null, null);
        int i = cursor2.getCount();
        int j = 0;
        String [] data = new String[i];
        if (cursor2.moveToFirst()){

            int spinnerIndex2 = cursor2.getColumnIndex(DBHelper.KEY_SPINNER);
            do {
                data[j] = cursor2.getString(spinnerIndex2);
                j++;
            }while (cursor2.moveToNext());

        } else Log.d("mLog2","0 rows");
        cursor2.close();
        dbHelper.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCM1.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        String selected = spCM1.getSelectedItem().toString();
        String profit = "0";
        if (!TextUtils.isEmpty(etCM1.getText().toString()))
        {
            profit = etCM1.getText().toString();
        }
        int profit2 = Integer.parseInt(profit);
        String category =  selected.toString();
        String title = etCM2.getText().toString();
        String status = "Расход";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int summa = 0;
        String summa1 = null;
        int i = 0;

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.bCM1:
                String days = spCM2.getSelectedItem().toString();
                String months = spCM3.getSelectedItem().toString();
                int days1 = Integer.parseInt(days);
                int months1 = Integer.parseInt(months);
                if (spCM2.getVisibility() == View.VISIBLE)
                {
                    day = days1;
                    month = months1;
                }
                contentValues.put(DBHelper.KEY_PROFIT, profit2);
                contentValues.put(DBHelper.KEY_CATEGORY, category);
                contentValues.put(DBHelper.KEY_STATUS, status);
                contentValues.put(DBHelper.KEY_TITLE, title);
                contentValues.put(DBHelper.KEY_YEAR, year);
                contentValues.put(DBHelper.KEY_MONTH, month);
                contentValues.put(DBHelper.KEY_DAY, day);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);

                break;
            case R.id.bCM2:
                String  a = null;
                String[] b = null;

                a = "status == ?";
                b = new String[]{"Расход"};

                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, a, b, null, null, null);
                Cursor cursor1 = database.query(DBHelper.TABLE_SPIN, null, null, null, null, null, null);

                int countCursor = cursor.getCount();
                int k = 0;
                String [] arr = new String[countCursor];

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int profitIndex = cursor.getColumnIndex(DBHelper.KEY_PROFIT);
                    int categoryIndex = cursor.getColumnIndex(DBHelper.KEY_CATEGORY);
                    int statusIndex = cursor.getColumnIndex(DBHelper.KEY_STATUS);
                    int titleIndex = cursor.getColumnIndex(DBHelper.KEY_TITLE);
                    int yearIndex = cursor.getColumnIndex(DBHelper.KEY_YEAR);
                    int monthIndex = cursor.getColumnIndex(DBHelper.KEY_MONTH);
                    int dayIndex = cursor.getColumnIndex(DBHelper.KEY_DAY);

                    do {
                        Log.d("mLog1", "id = " + cursor.getInt(idIndex) +  // id
                                ", profit = " + cursor.getInt(profitIndex) + //
                                ", category = " + cursor.getString(categoryIndex) + //
                                ", status = " + cursor.getString(statusIndex) + //
                                ", title = " + cursor.getString(titleIndex) + //
                                ", year = " + cursor.getString(yearIndex) + //
                                ", month = " + cursor.getString(monthIndex) + //
                                ", day = " + cursor.getString(dayIndex) //
                        );

                        arr[k] = ("Категория:  " + cursor.getString(categoryIndex) + "; Расход:  " + cursor.getString(profitIndex) + "; Год:  " + cursor.getString(yearIndex) + "; Месяц:  " + cursor.getString(monthIndex) + "; День:  " + cursor.getString(dayIndex) + "; Описание:  " + cursor.getString(titleIndex));
                        k++;
                        summa += cursor.getInt(profitIndex);
                    } while (cursor.moveToNext());
                } else Log.d("mLog", "0 rows");


                ArrayAdapter<String> adapter1;
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, arr);
                // Привяжем массив через адаптер к ListView
                gvCM1.setAdapter(adapter1);
                cursor.close();

                summa1 = Integer.toString(summa);
                tvCM3.setText(summa1);

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
                spCM2.setVisibility(View.VISIBLE);
                spCM3.setVisibility(View.VISIBLE);
                break;

        }
        dbHelper.close();
    }
}


