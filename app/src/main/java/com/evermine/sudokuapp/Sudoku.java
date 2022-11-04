package com.evermine.sudokuapp;

public class Sudoku {
    private int[][] sudoku = new int[9][9];

    public Sudoku(){
        for (int i = 0;i< sudoku.length;i++) {
            for (int j = 0; j < sudoku.length; j++) {
                sudoku[i][j] = 0;
            }

        }
    }

    public int getSudokuValue(int i,int j){
        return sudoku[i][j];
    }

    public  void setSudokuvalue(int i,int j,int value){
        sudoku[i][j]=value;
    }
}
