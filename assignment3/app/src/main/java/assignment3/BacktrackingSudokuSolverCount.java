package assignment3;

import java.util.Scanner;

// To run: java -jar app/build/libs/app.jar from root

public class BacktrackingSudokuSolverCount {
    static int count;

    // Check if clues are consistent with constraints (can also check solved sudoku as it is equivalent to a sudoku filled with clues)
    public static boolean isFeasible(int[][] sudoku, int nSize) {
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
        return feasible;
    }

    public static int backtrackingSudokuSolver(int[][] sudoku, int i, int j, int nSize) {

        // Check if sudoku is complete (out of grid)
        if (i == sudoku.length+1 && j == 1) {
            count++;
            return count;
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

        // check if cell is empty 
        if (sudoku[i-1][j-1] != 0) {
            if (backtrackingSudokuSolver(sudoku, iHat, jHat, nSize) == 1) {
                return 1;
            } 
        } 
        else {
            for (int k = 1; k <= sudoku.length; k++) {
                // Check if number can be placed
                if (CanPlace.canPlace(sudoku, i, j, k, nSize)) {
                    sudoku[i-1][j-1] = k; 
                    if (backtrackingSudokuSolver(sudoku, iHat, jHat, nSize) == 1) {
                        return 1;
                    } 
                    else {
                        sudoku[i-1][j-1] = 0;
                    }
                }
            }
        } 
        return count;
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

        
        if (isFeasible(sudoku, nSize)) {
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

// ** INPUT EXAMPLES ** // 

// 2
// 0 1 0 0
// 2 3 1 0
// 0 0 3 0
// 0 0 0 0

// 3
// 3 0 6 5 0 8 4 0 0
// 5 2 0 0 0 0 0 0 0
// 0 8 7 0 0 0 0 3 1
// 0 0 3 0 1 0 0 8 0
// 9 0 0 8 6 3 0 0 5
// 0 5 0 0 9 0 6 0 0
// 1 3 0 0 0 0 2 5 0
// 0 0 0 0 0 0 0 7 4
// 0 0 5 2 0 6 3 0 0

// 3
// 1 2 3 0 0 0 0 0 0
// 4 5 6 0 0 0 0 0 0
// 7 8 9 0 0 0 0 0 0
// 0 0 0 1 2 3 0 0 0
// 0 0 0 4 5 6 0 0 0
// 0 0 0 7 8 9 0 0 0
// 0 0 0 0 0 0 1 2 3
// 0 0 0 0 0 0 4 5 6
// 0 0 0 0 0 0 7 8 9