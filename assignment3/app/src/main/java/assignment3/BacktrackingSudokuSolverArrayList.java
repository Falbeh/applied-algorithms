package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ** This version is using Java collection List and ArrayList ** //
// To run: java -jar app/build/libs/app.jar from root

// ** INPUT EXAMPLE ** // 
// 2
// 0 1 0 0
// 2 3 1 0
// 0 0 3 0
// 0 0 0 0

public class BacktrackingSudokuSolverArrayList {
    static int nSize;

    public static boolean backtrackingSudokuSolveArrayList(List<List<Integer>> sudoku, int i, int j) {
        if (i == sudoku.size()+1 && j == 1) {
            return true;
        }
        
        // Next cell variables
        int iHat;
        int jHat;

        // Find next cell
        if (j < nSize*nSize) {
            iHat = i;
            jHat = j+1;
        }
        else {
            iHat = i+1;
            jHat = 1;
        }

        // System.out.println(i + " " + j);
        for (int k = 1; k <= nSize*nSize; k++) {
            
            // check if cell is empty 
            if (sudoku.get(i-1).get(j-1) != 0) {
                if (backtrackingSudokuSolveArrayList(sudoku, iHat, jHat)) {
                    return true;
                }
                else {
                    sudoku.get(i-1).set(j-1,0);
                }
            } 

            // Check if number can be placed
            if (CanPlaceArrayList.canPlaceArrayList(sudoku, i, j, k, nSize)) {
                // System.out.println(k);
                sudoku.get(i-1).set(j-1,k); 

                // Call method recursively
                if (backtrackingSudokuSolveArrayList(sudoku, iHat, jHat)) {
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
                        if (CanPlaceArrayList.canPlaceArrayList(sudoku, i+1, j+1, k, nSize)) {
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
            System.out.println(backtrackingSudokuSolveArrayList(sudoku, 1, 1)); 

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