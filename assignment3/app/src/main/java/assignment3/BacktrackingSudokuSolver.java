package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// To run: java -jar app/build/libs/app.jar from root

// ** INPUT EXAMPLE ** // 
// 2
// 0 1 0 0
// 2 3 1 0
// 0 0 3 0
// 0 0 0 0

public class BacktrackingSudokuSolver {
    static int nSize;

    public static boolean BacktrackingSudokuSolve(List<List<Integer>> sudoku, int i, int j) {
        if (i == sudoku.size()+1 && j == 1) {
            return true;
        }
        
        // Next cell variables
        int iPrime;
        int jPrime;

        // Find next cell
        if (j < nSize*nSize) {
            iPrime = i;
            jPrime = j+1;
        }
        else {
            iPrime = i+1;
            jPrime = 1;
        }

        // System.out.println(i + " " + j);
        for (int k = 1; k <= nSize*nSize; k++) {
            
            // check if cell is empty 
            if (sudoku.get(i-1).get(j-1) != 0) {
                if (BacktrackingSudokuSolve(sudoku, iPrime, jPrime)) {
                    return true;
                }
                else {
                    sudoku.get(i-1).set(j-1,0);
                }
            } 

            // Check if number can be placed
            if (CanPlace.canPlace(sudoku, i, j, k, nSize)) {
                // System.out.println(k);
                sudoku.get(i-1).set(j-1,k); 

                // Call method recursively
                if (BacktrackingSudokuSolve(sudoku, iPrime, jPrime)) {
                    return true;
                }
                else {
                    sudoku.get(i-1).set(j-1,0);
                }
            }
        } 
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        nSize = in.nextInt();

        List<List<Integer>> sudoku = new ArrayList<>();

        // Adding sudoku elements from stdin to sudoku list of lists
        for (int i = 0; i < nSize * nSize; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < nSize * nSize; j++) {
                row.add(in.nextInt());
            }
            sudoku.add(row);
        }
        in.close();

        // Check if partial solution is consistent with constraints 
        boolean feasible = true;
        for (int i = 0; i < nSize*nSize; i++) {
            if (feasible == true) {
                for (int j = 0; j < nSize*nSize; j++) {
                    if (sudoku.get(i).get(j) != 0) {
                        int k = sudoku.get(i).get(j);
                        sudoku.get(i).set(j,0);
                        if (CanPlace.canPlace(sudoku, i+1, j+1, k, nSize)) {
                            sudoku.get(i).set(j, k);
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
            System.out.println(BacktrackingSudokuSolve(sudoku, 1, 1)); 

            // Print solved sudoku
            for(List<Integer> l : sudoku) {
                for (int i : l) {
                    System.out.print(i + " ");
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