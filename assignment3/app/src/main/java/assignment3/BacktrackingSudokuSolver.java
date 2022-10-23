package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// To run: java -jar app/build/libs/app.jar from root

// ** INPUT EXAMPLE ** // 
// 2 4
// 0 1 0 0
// 2 3 1 0
// 0 0 3 0
// 0 0 0 0

public class BacktrackingSudokuSolver {
    static int nSize;

    public static boolean BacktrackingSudokuSolve(List<List<Integer>> sudoku, int i, int j) {
        if (i == sudoku.size() && j == sudoku.size()+1) {
            return true;
        }
        for (int k = 0; k < nSize*nSize; k++) {
            if (CanPlace.canPlace(sudoku, i, j, k)) {
                
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

        System.out.println(BacktrackingSudokuSolve(sudoku, 0, 0)); 
    }
}
