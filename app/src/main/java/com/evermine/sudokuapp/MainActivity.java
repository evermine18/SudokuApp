package com.evermine.sudokuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.material.tabs.TabLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static Spinner spinnerList[][] = new Spinner[9][9];
    private static Sudoku sudoku = new Sudoku();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CharSequence[] nombres = {"0","1","2","3","4","5","6","7","8","9"};

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
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        int fila = (int) adapterView.getTag(R.id.col);
                        int col = (int) adapterView.getTag(R.id.fila);
                        if(!sudoku.setSudokuvalue(col, fila, adapterView.getSelectedItemPosition()) && adapterView.getSelectedItemPosition() != 0){
                            spinnerList[col][fila].setBackgroundResource(R.drawable.spinnerwrong_background);
                            spinnerList[col][fila].setSelection(sudoku.getSudokuValue(col,fila));
                        }else{
                            spinnerList[col][fila].setBackground(null);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                spinnerList[y][x]=spinner;
                //spinner.setSelection(4);
                //System.out.println(sudoku.getSudokuValue(y,x));
            }
            tl.addView(row);

        }
        startGame();

    }

    public static void updateView(){
        for (int i = 0;i<9;i++){
            for (int j = 0;j<9;j++){
                spinnerList[i][j].setSelection(sudoku.getSudokuValue(i,j));
                spinnerList[i][j].setBackground(null);
                //spinnerList[i][j].setEnabled(false);
            }
        }
        sudoku.showSudoku();
    }

    public static void startGame(){
        Random rand = new Random();
        int number = 15;
        while(number>0){
            int i = rand.nextInt(9);
            int j = rand.nextInt(9);
            System.out.println(i+ "j "+j);
            if(sudoku.setSudokuvalue(i,j,rand.nextInt(9)+1)==true){
                number--;
                spinnerList[i][j].setEnabled(false);
            }else{
                sudoku.setSudokuvalue(i,j,0);
            }
        }
        updateView();

    }

    public static void selectSpinnerItemByValue(Spinner spnr, long value) {
        SimpleCursorAdapter adapter = (SimpleCursorAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }
}