package assignment3.Backtracking;

import java.util.Random;

public class RandomSudokuGenerator {

    public static boolean subgridChecker(int[][] sudoku, int i, int j, int k, int n) {
        boolean canPlace = false;
        // Identify which subgrid we are looking in from 0 to nSize^2 subgrids
        int startRow = (i - 1) - (i - 1) % n;
        int startCol = (j - 1) - (j - 1) % n;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (sudoku[row+startRow][col+startCol] != k) {
                    canPlace = true;
                } else {
                    return false;
                }
            }
        }
        if (canPlace) {
            return true;
        }
        else {
            return false;
        }
    }

    public static int[][] generateRandomUnique(int n, int s) {

        Random random = new Random();
        random.setSeed(s); // Seed for random method

        // Initializing array with random seeds for each cell
        int[][] seeds = new int[n * n][n * n];
        for (int i = 0; i < seeds.length; i++) {
            for (int j = 0; j < seeds.length; j++) {
                seeds[i][j] = random.nextInt(1000000);
            }
        }

        // Initialize empty sudoku
        int[][] sudoku = new int[n * n][n * n];

        // Filling sudoku with random numbers
        // If sudoku is 9x9 or larger we fill the diagonal subgrids first with random
        // numbers and then recursively fill rest using backtracking algorithm
        if (n >= 3) {
            // Finding first cell of each diagonal subgrid
            for (int f = 0; f < sudoku.length; f = f + n) {
                // Fill diagonal subgrids with random numbers
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        while (sudoku[i + f][j + f] == 0) {
                            // Setting the seed for the cell
                            random.setSeed(seeds[i + f][j + f]);
                            // Generate the random number for cell
                            int k = random.nextInt(n * n) + 1;
                            // Check if the number can be placed in cell
                            if (subgridChecker(sudoku, i + f + 1, j + f + 1, k, n)) {
                                sudoku[i + f][j + f] = k;
                                break;
                            }
                            // if can't be placed in cell we try with new random number by incrementing seed
                            seeds[i + f][j + f]++;
                        }
                    }
                }
            }
        // Otherwise if sudoku is only 4x4 or 2x2 we fill random row with random numbers
        } else {
            int row = random.nextInt(n*n);
            for (int j = 0; j < sudoku.length; j++) {
                while (sudoku[row][j] == 0) {
                    random.setSeed(seeds[row][j]);
                    int k = random.nextInt(n * n) + 1;

                    if (CanPlace.canPlace(sudoku, row+1, j+1, k, n)) {
                        sudoku[row][j] = k;
                        break;
                    }
                    // if can't be placed in cell we try with new random number by incrementing seed
                    seeds[row][j]++;
                }
            }
        }

        // Fill rest of sudoku recursively using backtracking algorithm
        BacktrackingSudokuSolver.backtrackingSudokuSolver(sudoku, 1, 1, n);
        // Saving temporary variables, so we can place the last removed number back in the cell again
        int tk=0;
        int ti=0;
        int tj=0;
        // Remove random numbers until 2 solutions to find sudoku with 1 unique solution
        while (BacktrackingSudokuSolverCount.SudokuSolverCount(sudoku, 1, 1, n) == 1) {
            ti = random.nextInt(n * n) + 1;
            tj = random.nextInt(n * n) + 1;
            tk = sudoku[ti-1][tj-1];
            sudoku[ti-1][tj-1] = 0;
            BacktrackingSudokuSolverCount.resetCount();
        }

        sudoku[ti-1][tj-1] = tk;

        // Print sudoku with 1 unique solution
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }

        return sudoku;
    }

    public static void main(String[] args) {
        generateRandomUnique(4, 381241);
    }
}