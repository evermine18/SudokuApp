package com.evermine.sudokuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CharSequence[] nombres = {"0","1","2","3","4","5"};

        TableLayout tl = findViewById(R.id.tableLayout);
        for (int i = 0; i<5; i++){
            TableRow row = new TableRow(this);
            Spinner spinner = new Spinner(this);
            Spinner spinner2 = new Spinner(this);
            Spinner spinner3 = new Spinner(this);
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                    android.R.layout.simple_spinner_item, nombres);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner2.setAdapter(adapter);
            spinner3.setAdapter(adapter);
            row.addView(spinner);
            row.addView(spinner2);
            row.addView(spinner3);
            tl.addView(row);
        }

    }
}