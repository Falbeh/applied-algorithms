package assignment3.DancingLinks;
import java.util.Scanner;

/*
 * EXAMPLE INPUT 
 * 
 * 2
 * 0 0 0 3
 * 0 4 0 0
 * 0 1 0 0
 * 0 3 2 0
 */

public class SudokuToExactCover {

    public static int[][] reductionToExactCover(int[][] sudoku, int n) {

        // Data structure to store exact cover with n^6 rows and 4*n^4 columns
        int exactCover[][] = new int[(int)Math.pow(n, 6)][4*(int)Math.pow(n, 4)]; 

        /**
         * Constraint (i) row
         */
        // For each row in exactCover int[][] array
        int row=0;
        // For each row in sudoku
        for (int i = 0; i < sudoku.length; i++) {
            // For each column in sudoku
            for (int j = 0; j < sudoku.length; j++) {
                // For each possible number in sudoku
                for (int k = 1; k <= sudoku.length; k++) {
                    if (sudoku[i][j] == k || sudoku[i][j] == 0) {
                        exactCover[row][i*n*n+k-1] = 1;
                    }
                    row++;
                }
            }
        }
        
        /**
         * Constraint (ii) column 
         */
        row=0;
        int startCol = (int)(Math.pow(n, 4));
        // For each row in sudoku
        for (int i = 0; i < sudoku.length; i++) {
            // For each column in sudoku
            for (int j = 0; j < sudoku.length; j++) {
                // For each possible number in sudoku
                for (int k = 1; k <= sudoku.length; k++) {
                    if (sudoku[i][j] == k || sudoku[i][j] == 0) {
                        exactCover[row][startCol+(j*n*n+k-1)] = 1;
                    }
                    row++;
                }
            }
        }
        
        /**
         * Constraint (iii) subgrid
         */
        row=0;
        startCol = (int)(2*Math.pow(n, 4));
        // For each row in sudoku
        for (int i = 0; i < sudoku.length; i++) {
            // For each column in sudoku
            for (int j = 0; j < sudoku.length; j++) {
                // For each possible number in sudoku
                for (int k = 1; k <= sudoku.length; k++) {
                    if (sudoku[i][j] == k || sudoku[i][j] == 0) {
                        int subgrid = (((i)/n)*n+((j)/n)+1);
                        exactCover[row][startCol+((subgrid-1)*n*n+k)-1] = 1;
                    }
                    row++;
                }
            }
        }

        /**
         * Constraint (iv) number in i,j
         */
        row=0;
        startCol = (int)(3*Math.pow(n, 4));
        // For each row in sudoku
        for (int i = 0; i < sudoku.length; i++) {
            // For each column in sudoku
            for (int j = 0; j < sudoku.length; j++) {
                // For each possible number in sudoku
                for (int k = 1; k <= sudoku.length; k++) {
                    if (sudoku[i][j] == k || sudoku[i][j] == 0) {
                        exactCover[row][startCol+((i*n*n)+j)] = 1;
                    }
                    row++;
                }
            }
        }

        /* for (int i = 0; i < exactCover.length; i++) {
            for (int j = 0; j < exactCover[0].length; j++) {
                System.out.print(exactCover[i][j]);
            }
            System.out.println();
        } */

        return exactCover;
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

        reductionToExactCover(sudoku, nSize);
        
        
    }
}
