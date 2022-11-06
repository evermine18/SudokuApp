package com.evermine.sudokuapp;

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
    * @param i References to column
    * @param j References to row
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
}
