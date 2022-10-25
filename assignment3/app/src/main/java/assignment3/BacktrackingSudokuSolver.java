package assignment3;

import java.util.Scanner;

// To run: java -jar app/build/libs/app.jar from root

// ** INPUT EXAMPLE ** // 
// 2
// 0 1 0 0
// 2 3 1 0
// 0 0 3 0
// 0 0 0 0

public class BacktrackingSudokuSolver {

    public static boolean backtrackingSudokuSolver(int[][] sudoku, int i, int j, int nSize) {
        // Check if sudoku is complete (out of grid)
        if (i == sudoku.length+1 && j == 1) {
            return true;
        }
        
        // Next cell variables
        int iHat;
        int jHat;

        // Find next cell
        if (j < sudoku.length) {
            iHat = i;
            jHat = j+1;
        }
        else {
            iHat = i+1;
            jHat = 1;
        }

        // System.out.println(i + " " + j);
        for (int k = 1; k <= sudoku.length; k++) {
            
            // check if cell is empty 
            if (sudoku[i-1][j-1] != 0) {
                if (backtrackingSudokuSolver(sudoku, iHat, jHat, nSize)) {
                    return true;
                }
                else {
                    sudoku[i-1][j-1] = 0;
                }
            } 

            // Check if number can be placed
            if (CanPlace.canPlace(sudoku, i, j, k, nSize)) {
                // System.out.println(k);
                sudoku[i-1][j-1] = k; 

                // Call method recursively
                if (backtrackingSudokuSolver(sudoku, iHat, jHat, nSize)) {
                    return true;
                }
                else {
                    sudoku[i-1][j-1] = 0;
                }
            }
        } 
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nSize = in.nextInt();

        int[][] sudoku = new int[nSize*nSize][nSize*nSize];

        // Adding sudoku elements from stdin to sudoku list of lists
        for (int i = 0; i < nSize * nSize; i++) {
            for (int j = 0; j < nSize * nSize; j++) {
                sudoku[i][j] = in.nextInt();
            }
        }
        in.close();

        // Check if partial solution is consistent with constraints 
        boolean feasible = true;
        for (int i = 0; i < nSize*nSize; i++) {
            if (feasible == true) {
                for (int j = 0; j < nSize*nSize; j++) {
                    if (sudoku[i][j] != 0) {
                        int k = sudoku[i][j];
                        sudoku[i][j] = 0;
                        if (CanPlace.canPlace(sudoku, i+1, j+1, k, nSize)) {
                            sudoku[i][j] = k;
                        }
                        else {
                            feasible = false;
                            break;
                        }
                    }
                }
            }
        }

        
        if (feasible) {
            // Run backtracking on
            System.out.println(backtrackingSudokuSolver(sudoku, 1, 1, nSize)); 

            // Print solved sudoku
            for(int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku.length; j++) {
                    System.out.print(sudoku[i][j] + " "); ;
                }
                System.out.println();
            }
        }
        else {
            System.out.println("Partial solution is inconsistent with constraints");
        }
        
    }
}

// 4123
// 2314
// 1432
// 3241

// ISSUE - this should not be solvable but the algorithm changes clues
// 4 1 2 3
// 2 3 1 4
// 1 2 3 0
// 0 0 0 0