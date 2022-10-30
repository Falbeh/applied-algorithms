package assignment3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SATToSudoku {

    public static int[][] satToSudoku() {

        List<Integer> sudokuSAT = new ArrayList<>();

        // Fill sudokuSAT list with result integers
        try {
            File file = new File("cnf/results.cnf");
            Scanner in = new Scanner(file);

            String s = in.next();
            String satisfiable = in.next();

            if (satisfiable.equals("SATISFIABLE")) {
                while (in.hasNext()) {
                    String next = in.next();
                    if (!next.equals("v")) {
                        sudokuSAT.add(Integer.parseInt(next));
                    }
                }
            } else {
                System.out.println("Sudoku is unsatisfiable");
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }

        int variables = sudokuSAT.size() - 1; // -1 because it also adds 0 in the end
        int n = (int) Math.round(Math.pow(variables, 1.0 / 6.0)); // Taking the 6th root of variables to find n

        int[][][] cells = new int[n * n][n * n][n * n];

        int index = 0;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                for (int k = 0; k < cells.length; k++) {
                    cells[i][j][k] = sudokuSAT.get(index);
                    index++;
                }
            }
        }

        int[][] sudoku = new int[n * n][n * n];

        // Fill sudoku
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                for (int k = 0; k < cells.length; k++) {
                    if (cells[i][j][k] > 0) {
                        sudoku[i][j] = k+1;
                        break;
                    }
                }
            }
        }

        // Print sudoku
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }

        return null;
    }

    public static void main(String[] args) {
        satToSudoku();
    }
}
