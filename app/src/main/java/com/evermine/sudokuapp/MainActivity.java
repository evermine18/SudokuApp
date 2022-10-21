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
        Spinner spinnerList[][] = new Spinner[8][8];
        CharSequence[] nombres = {"1","2","3","4","5","6","7","8","9"};

        TableLayout tl = findViewById(R.id.tableLayout);
        for (int y = 0; y<9; y++){
            TableRow row = new TableRow(this);
            for (int x = 0; x<9; x++){
                Spinner spinner = new Spinner(this);
                spinner.setBackground(null);
                spinner.setPadding(5, 5, 5, 5);
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, nombres);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                row.addView(spinner);
                spinner.setTag(R.id.col,x);
                spinner.setTag(R.id.fila,y);
                spinnerList[y][x]=spinner;
            }
            tl.addView(row);
        }
        /*
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
        */
    }
}