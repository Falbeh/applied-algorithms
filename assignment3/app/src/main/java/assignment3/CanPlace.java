package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CanPlace {
    // static int nSize;

    public static boolean canPlace(List<List<Integer>> sudoku, int i, int j, int k, int nSize) {
        boolean canPlace = false;
        // Check if cell is already filled
        if (sudoku.get(i - 1).get(j - 1) == 0) { // -1 because of index of array starting from 0 and not 1
            // Check if row i already has number k
            if (!sudoku.get(i - 1).contains(k)) {
                // Check if column j contain number k
                for (int c = 0; c < nSize * nSize; c++) {
                    if (sudoku.get(c).get(j - 1) != k) {
                        // Identify which subgrid we are looking in from 0 to nSize^2 subgrids
                        int startRow = (i - 1) - (i - 1) % nSize;
                        int startCol = (j - 1) - (j - 1) % nSize;

                        for (int row = 0; row < nSize; row++) {
                            for (int col = 0; col < nSize; col++) {
                                if (sudoku.get(row + startRow).get(col + startCol) != k) {
                                    canPlace = true;
                                } else {
                                    return false;
                                }
                            }
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        if (canPlace == true) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        /* Scanner in = new Scanner(System.in);
        nSize = in.nextInt();
        int mQueries = in.nextInt();

        List<List<Integer>> sudoku = new ArrayList<>();

        // Adding sudoku elements from stdin to sudoku list of lists
        for (int i = 0; i < nSize * nSize; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < nSize * nSize; j++) {
                row.add(in.nextInt());
            }
            sudoku.add(row);
        }

        // Reading queries
        for (int q = 0; q < mQueries; q++) {
            int i = in.nextInt();
            int j = in.nextInt();
            int k = in.nextInt();

            if (CanPlace.canPlace(sudoku, i, j, k) == true) {
                System.out.println("TRUE");
            } else {
                System.out.println("FALSE");
            }
        } */
    }
}