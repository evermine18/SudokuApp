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

    public boolean setSudokuvalue(int i,int j,int value){
        boolean isCorrect = true;
        for (int x = 0; x<sudoku[i].length; x++){
            System.out.println("First " + sudoku[i][x]);
            if (value==sudoku[i][x]){
                isCorrect=false;
            }
        }
        for (int y = 0; y<sudoku.length; y++){
            System.out.println("Second "+sudoku[y][j]);
            if (value==sudoku[y][j]){
                isCorrect=false;
            }
        }
        if (isCorrect==true) {
            sudoku[i][j]=value;
        }
        return isCorrect;
    }
}
