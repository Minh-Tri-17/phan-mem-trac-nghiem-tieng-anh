package com.example.baocaoandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    LinearLayout lnLT, lnLST;
    TextView textHight;
    int RESULT_CODE = 1;
    int hightScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addViews();
        addEvent();

    }

    public void addEvent(){
        lnLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                startActivity(intent);
            }
        });
        lnLST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,History.class);
                startActivity(intent);
            }
        });
    }

    public void addViews(){
        lnLT = findViewById(R.id.linerLT);
        lnLST = findViewById(R.id.linerLST);
    }
}