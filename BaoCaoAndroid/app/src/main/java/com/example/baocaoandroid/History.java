package com.example.baocaoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baocaoandroid.model.Result;

import java.util.List;

public class History extends AppCompatActivity {

    ListView listViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        addView();

        loadResult();
    }
    private void loadResult(){
        Database database = new Database(this);
        List<Result> results = database.getResult();

        ArrayAdapter<Result> resultArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,results);

        listViewResult.setAdapter(resultArrayAdapter);
    }

    private void addView(){
        listViewResult = findViewById(R.id.lsvResult);
    }
}