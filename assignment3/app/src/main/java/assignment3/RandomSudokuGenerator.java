package assignment3;

import java.util.Random;

public class RandomSudokuGenerator {
    

    public static int[][] generateRandomUnique(int n, int s) {

        Random random = new Random();
        random.setSeed(s); // Seed for random method

        // Initializing array with random seeds for each cell 
        int[][] seeds = new int[n*n][n*n];
        for (int i = 0; i < seeds.length; i++) {
            for (int j = 0; j < seeds.length; j++) {
                seeds[i][j] = random.nextInt(1000000);
            }
        }
        
        /* // Print seeds
        for (int i = 0; i < seeds.length; i++) {
            System.out.println(seeds[i]);
        } */

        // Initialize empty sudoku
        int[][] sudoku = new int[n*n][n*n];
        int[][] copySudoku = new int[n*n][n*n];

        boolean check=false;
        // Fill sudoku with random numbers
        for (int i = 0; i < sudoku.length; i++) {
            if (!check) {

            
            for (int j = 0; j < sudoku.length; j++) {
                

                // Try n^4 random numbers 
                for (int c = 0; c < Math.pow(n,4); c++) {
                    // Setting the seed for the cell
                    random.setSeed(seeds[i][j]);
                    // Generate the random number for cell
                    int k = random.nextInt(n*n)+1;
                    // Check if the number can be placed in cell
                    if (CanPlace.canPlace(sudoku, i+1, j+1, k, n) && BacktrackingSudokuSolver.backtrackingSudokuSolver(copySudoku, i+1, j+1, n)) {
                        System.out.println(k + " placed in cell " + i+","+j);
                        sudoku[i][j] = k;
                        copySudoku[i][j] = k;
                        break;
                    }
                    else {
                        seeds[i][j]++;
                    }
                }
                // If cell is still empty we will backtrack
                if (sudoku[i][j] == 0) {
                    System.out.println(i+","+j);
                    BacktrackingSudokuSolver.backtrackingSudokuSolver(sudoku, i+1, j+1, n); 
                    check = true;
                }
                
                
    
                
            }
        }
        } 
        
        
        // Print sudoku
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                System.out.print( sudoku[i][j] + " ");
            }
            System.out.println();
        }

        return null;
    }


    public static void main(String[] args) {
        
        generateRandomUnique(3, 381241);
    }
}