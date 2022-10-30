package assignment3;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SudokuToSAT {
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[][] sudoku = new int[n*n][n*n];

        // Adding sudoku elements from stdin to sudoku list of lists
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                sudoku[i][j] = in.nextInt();
            }
        }
        in.close();

        // Counting clues
        int countClues=0;
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                if (sudoku[i][j] != 0) {
                    countClues++;
                }
            }
        }

        // Creating a file to store DIMACS
        File cnf = new File("cnf/dimacs.cnf");
        FileWriter cnfGen = new FileWriter("cnf/dimacs.cnf");

        // nVariables and nClauses
        int nVariables = (int)(Math.pow(n, 6));
        int nClauses = (int)(4*Math.pow(n, 4)+(((Math.pow(n, 8))-(Math.pow(n, 6)))/2))+countClues;

        // Encode cells as numbers
        int[][][] cells = new int[n*n][n*n][n*n];
        int number = 1;
        for (int i = 0; i < n*n; i++) {
            for (int j = 0; j < n*n; j++) {
                for (int k = 0; k < n*n; k++) {
                    cells[i][j][k] = number;
                    number++;
                }
            }
        }
        

        // Print dimacs header
        cnfGen.write("p cnf " + nVariables + " " + nClauses);
        cnfGen.write(System.getProperty( "line.separator" ));

        // Constraint (i) Every number must occur on every row, yielding n^4 clauses in total

        // For every row 
        for (int i = 0; i < cells.length; i++) {
             // For every number
            for (int k = 0; k < n*n; k++) {
                // for every col 
                for (int j = 0; j < n*n; j++) {
                    cnfGen.write(cells[i][j][k] + " ");
                }
                cnfGen.write("0");
                cnfGen.write(System.getProperty( "line.separator" ));
            }
        }

        // Constraint (ii) Every number must occur on every column, yielding n^4 clauses in total
       
        // For every column 
        for (int j = 0; j < cells.length; j++) {
            // For every number 
            for (int k = 0; k < n*n; k++) {
                // for every row 
                for (int i = 0; i < n*n; i++) {
                    cnfGen.write(cells[i][j][k] + " ");
                   
                }
                cnfGen.write("0");
                cnfGen.write(System.getProperty( "line.separator" ));
            }
        }

        // Constraint (iii) Every number must occur in every n×n subgrid, yielding n^4 clauses in total

        // For every subgrid in col s to cells.length
        for (int c = 0; c < cells.length; c=c+n) {
            int subgridc = c;
            // For every subgrid in row r to cells.length
            for (int r = 0; r < cells.length; r=r+n) {
                int subgridr = r;
                // For every number in cell
                for (int k = 0; k < cells.length; k++) {
                    // For every row in subgrid
                    for (int i = subgridc; i < subgridc+n; i++) {
                        // For every col in subgrid 
                        for (int j = subgridr; j < subgridr+n; j++) {
                            cnfGen.write(cells[i][j][k] + " ");
                        }
                    }
                    cnfGen.write("0");
                    cnfGen.write(System.getProperty( "line.separator" ));
                }
            }
        }

        // Constraint (iv) Every cell must contain a number
        // For every row 
        for (int i = 0; i < cells.length; i++) {
            // For every col
           for (int j = 0; j < n*n; j++) {
               // for every number 
               for (int k = 0; k < n*n; k++) {
                   cnfGen.write(cells[i][j][k] + " ");
               }
               cnfGen.write("0");
               cnfGen.write(System.getProperty( "line.separator" ));
           }
        }

        // Constraint (v) Every cell must contain at most 1 number
        int combinations = (n*n*((n*n)-1))/2; // How many different pairs can there be in 1 cell

        // For every row
        for (int i = 0; i < cells.length; i++) {
            // For every col
            for (int j = 0; j < n*n; j++) {
    
                // Idea: Make a list with numbers that can be in that cell and print all combinations using some java lib?
                List<Integer> combinationNumbers = new ArrayList<>();
                for (int l = 0; l < cells.length; l++) {
                    combinationNumbers.add(cells[i][j][l]);
                }
                
                for (int l = 0; l < cells.length; l++) { 
                    int firstEl = combinationNumbers.remove(0);
                    for (int rest = 0; rest < combinationNumbers.size(); rest++) {  
                        cnfGen.write(-firstEl + " " + -combinationNumbers.get(rest) + " 0");
                        cnfGen.write(System.getProperty( "line.separator" ));
                    }
                } 
           }
        }

        // Constrain (vi) Consider all clues
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                if (sudoku[i][j] != 0) {
                    int k = sudoku[i][j];
                    cnfGen.write(cells[i][j][k-1] + " 0");
                    cnfGen.write(System.getProperty( "line.separator" ));
                }
            }
        }
        cnfGen.close();

    }
}

