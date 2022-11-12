package com.evermine.sudokuapp;

import java.util.Scanner;

public class Sudoku {
    private int[][] sudoku = new int[9][9];

    public Sudoku(){
        // When we initialize the constructor we fill all the values with 0
        for (int i = 0;i< sudoku.length;i++) {
            for (int j = 0; j < sudoku.length; j++) {
                sudoku[i][j] = 0;
            }

        }
    }

    public int getSudokuValue(int i,int j){
        return sudoku[i][j];
    }

    /*
    * This function tries to set a value and returns if the value is valid or not
    * @param i References to row
    * @param j References to column
    * @return boolean This return a boolean depending if the value is valid or not
     */
    public boolean setSudokuvalue(int i,int j,int value){
        boolean isCorrect = true;
        //For iteration that checks every column from the same row
        for (int x = 0; x<sudoku[i].length; x++){
            if (x!=j && value==sudoku[i][x] && sudoku[i][x]!=0){
                isCorrect=false; //If we detect the same value on a row, we set the value to false
            }
        }
        //For iteration that checks every row from the same column
        for (int y = 0; y<sudoku.length; y++){
            if (y!= i && value==sudoku[y][j] && sudoku[y][j]!=0){
                isCorrect=false; //If we detect the same value on a column, we set the value to false
            }
        }

        int squareI=getSquare(j);
        int squareJ=getSquare(i);
        //Checking square

        if(value!=0) {
            for (int x = (squareJ * 3); x < (squareJ * 3) + 3; x++) {
                for (int y = (squareI * 3); y < (squareI * 3) + 3; y++) {
                    if (sudoku[x][y] == value && x != i && y != j) {
                        isCorrect = false;
                    }
                    //System.out.println("x: "+x+"y: "+y);
                }
            }
        }

        sudoku[i][j]=value; //Finally we will set the value provided

        return isCorrect;
    }

    /*
     * This function make a print with the actual sudoku values
     *
     */
    public void showSudoku(){
        for (int i = 0;i<9;i++){
            for (int j = 0; j<9;j++){
                System.out.print(sudoku[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }
    public int getSquare(int pos){
        if(pos<=8 && pos>=6){
            return 2;
        }else if(pos<=5 && pos>=3){
            return 1;
        }
        return 0;
    }
}
