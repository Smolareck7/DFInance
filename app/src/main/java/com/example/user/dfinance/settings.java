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

public class settings extends AppCompatActivity implements View.OnClickListener {

    Button bS1;
    TextView tvS1;
    TextView tvS2;
    Spinner spS1;
    EditText etS1;
    DBHelper dbHelper1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bS1 = (Button) findViewById(R.id.bS1);
        bS1.setOnClickListener(this);
        tvS1 = (TextView) findViewById(R.id.tvS1);
        tvS2 = (TextView) findViewById(R.id.tvS2);
        etS1 = (EditText) findViewById(R.id.etS1);
        spS1 = (Spinner) findViewById(R.id.spS1);
        dbHelper1 = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {

        String stat = spS1.getSelectedItem().toString();
        String spinners =  etS1.getText().toString();

        SQLiteDatabase database = dbHelper1.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();

        switch (v.getId()) {
            case R.id.bS1:
                contentValues1.put(DBHelper.KEY_STAT, stat);
                contentValues1.put(DBHelper.KEY_SPINNER, spinners);
                database.insert(DBHelper.TABLE_SPIN, null, contentValues1);

                break;
        }
        dbHelper1.close();
    }
}

