package assignment3;

import java.io.File;
import java.util.Scanner;

import assignment3.Backtracking.BacktrackingSudokuSolver;
import assignment3.DancingLinks.DancingLinksSudokuSolver;

public class Horserace {
   
    public static void main(String[] args) {
      
        try {
            /* File file = new File("sudokusWithOneUniqueSolution/16x16.txt"); */
            File file = new File("sudokusWithOneUniqueSolution/16x16.txt");
            Scanner in = new Scanner(file);

            int n = in.nextInt();
            int[][] sudoku = new int[n*n][n*n];
    
            // Adding sudoku elements from stdin to sudoku list of lists
            for (int i = 0; i < n * n; i++) {
                for (int j = 0; j < n * n; j++) {
                    sudoku[i][j] = in.nextInt();
                }
            }
            System.out.println(n);
            in.close();
            /* BacktrackingSudokuSolver.backtrackingSudokuSolver(sudoku,1,1,n); */
            DancingLinksSudokuSolver.DLXSolver(sudoku, n);
            System.out.println("Algorithm succesfully ran");
        } catch (Exception e) {
            System.out.println("File not found");
        }
        
        
        /* if ("backtracking".equals(args[0])) {
            
        } else if ("satsolver".equals(args[0])) {
            // y = threeSumQuadratic(x);
        } else if ("dancinglinks".equals(args[0])) {
            DancingLinksSudokuSolver.DLXSolver(sudoku, n);
        }  */       
    }
   
}
