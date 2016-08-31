package com.example.user.dfinance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    Button b2;
    Button b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b4 = (Button) findViewById(R.id.b4);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.b1:
                Intent intent1 = new Intent(this, addMoney.class);
                startActivity(intent1);
                break;
            case R.id.b2:
                Intent intent2 = new Intent(this, cleanMoney.class);
                startActivity(intent2);
                break;
            case R.id.b4:
                Intent intent4 = new Intent(this, settings.class);
                startActivity(intent4);
                break;
            default:break;
        }

    }
}
