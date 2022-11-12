package com.evermine.sudokuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        //Value secuence for the spinner
        CharSequence[] nombres = {"0","1","2","3","4","5","6","7","8","9"};
        //TableLayout
        TableLayout tl = findViewById(R.id.tableLayout);
        //Button to resolve the Sudoku with backtracking
        Button resolve = findViewById(R.id.resolve);
        resolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resolveGame(0,0); //Calling resolveGame Function
                updateView(); //Updating view
            }
        });
        //Now we add a 9x9 spinner table simulating a sudoku
        for (int y = 0; y<9; y++){
            TableRow row = new TableRow(this);
            for (int x = 0; x<9; x++){
                Spinner spinner = new Spinner(this);
                //We need to change spinner design
                spinner.setBackground(null);
                spinner.setPadding(5, 5, 5, 5);
                //Adding the charsecuence values to spinner
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, nombres);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                row.addView(spinner); //Adding the spinner to the table
                //Setting the spinner tags
                spinner.setTag(R.id.col,x);
                spinner.setTag(R.id.fila,y);
                //Adding OnItemSelectedListener on every spinner
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //Getting the row and colum
                        int fila = (int) adapterView.getTag(R.id.col);
                        int col = (int) adapterView.getTag(R.id.fila);
                        //Trying to set the value to the model and refresh the view
                        if(!sudoku.setSudokuvalue(col, fila, adapterView.getSelectedItemPosition()) && adapterView.getSelectedItemPosition() != 0){
                            spinnerList[col][fila].setBackgroundResource(R.drawable.spinnerwrong_background);//Setting red background
                            spinnerList[col][fila].setSelection(sudoku.getSudokuValue(col,fila)); //Updating spinner selection
                        }else{
                            spinnerList[col][fila].setBackground(null);//Removing the background
                            spinnerList[col][fila].setSelection(sudoku.getSudokuValue(col,fila)); //Updating spinner selection
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                spinnerList[y][x]=spinner;//Adding spinner to spinners Array
            }
            tl.addView(row);//Adding the row to the table

        }
        startGame(); //Now we start the game adding 30 random values
    }

    /*
    * This method updates all spinners values with the model value
    *
     */
    public static void updateView(){
        for (int i = 0;i<9;i++){
            for (int j = 0;j<9;j++){
                spinnerList[i][j].setSelection(sudoku.getSudokuValue(i,j));
                spinnerList[i][j].setBackground(null);
            }
        }
    }

    /*
    * This method starts the game filling 30 values with a random number
    * from 1 to 9 and check if this value is valid
     */
    public static void startGame(){
        Random rand = new Random();
        int number = 30;
        while(number>0){
            int i = rand.nextInt(9); //Random col
            int j = rand.nextInt(9); //Random row
            System.out.println(i+ "j "+j);
            if(sudoku.setSudokuvalue(i,j,rand.nextInt(9)+1)==true){
                number--;
                spinnerList[i][j].setEnabled(false);//Setting spinner disabled
            }else{
                //If the value is not correct this function tries to change other value
                sudoku.setSudokuvalue(i,j,0);
            }
        }
        updateView();//Updating the view with the predefined values

    }

    /*
    * This recursive method resolves the game with backtracking algorithm
    * @param col This parameter references to column
    * @param row This parameter references to row
    * @return boolean This return a boolean var if the value is valid or not
     */

    public static boolean  resolveGame(int col, int row){
        //If the row is 9 it means that the sudoku has been solved
        System.out.println(""+col);
        if(row>=9){
            return true;
        }
        //If the col is 9 we need to call the function to resolve the following row
        else if(col>=9){
            return resolveGame(0,row+1);
        }
        //If we detect a value we need to skip this value, becose can be a predefined value
        //that the game has been generated
        else if(sudoku.getSudokuValue(col,row)!=0){
            return resolveGame(col+1,row);
        }

        //Now we need to do a for that try to set a value from 1 to 9
        //until we find the right one
        for(int i = 1;i<=9;i++){
            if (sudoku.setSudokuvalue(col, row, i)) {
                //sudoku.showSudoku();
                if(resolveGame(col+1,row)){
                    return true;
                }
            }
        }
        //If we dont find a valid value we will return false and the previous call will try with the following value
        sudoku.setSudokuvalue(col, row, 0);

        return false;
    }

}