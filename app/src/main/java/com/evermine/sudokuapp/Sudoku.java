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
            if (x!=j && value==sudoku[i][x] && sudoku[i][x]!=0){
                System.out.println(value+" Valor? "+sudoku[i][x]);
                isCorrect=false;
            }
        }
        for (int y = 0; y<sudoku.length; y++){
            if (y!= i && value==sudoku[y][j] && sudoku[y][j]!=0){
                isCorrect=false;
                System.out.println(value+" Valor? "+sudoku[y][j]);
            }
        }
        sudoku[i][j]=value;


        return isCorrect;
    }
    public void showSudoku(){
        for (int i = 0;i<9;i++){
            for (int j = 0; j<9;j++){
                System.out.print(sudoku[i][j]+" ");
            }
            System.out.println();
        }
    }
}
