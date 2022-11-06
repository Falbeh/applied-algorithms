package assignment3.Backtracking;

public class CanPlace {
    public static boolean canPlace(int[][] sudoku, int i, int j, int k, int nSize) {
        boolean canPlace = false;
        // Check if cell is already filled
        if (sudoku[i-1][j-1] == 0) { // -1 because of index of array starting from 0 and not 1
            // Check if row i already has number k
            boolean contains = false;
            for (int l = 0; l < sudoku.length; l++) {
                if (sudoku[i-1][l] == k) {
                    contains = true;
                    break;
                } 
            }
            if (!contains) {
                // Check if column j contain number k
                for (int c = 0; c < nSize * nSize; c++) {
                    if (sudoku[c][j-1] != k) {
                        // Identify which subgrid we are looking in from 0 to nSize^2 subgrids
                        int startRow = (i - 1) - (i - 1) % nSize;
                        int startCol = (j - 1) - (j - 1) % nSize;

                        for (int row = 0; row < nSize; row++) {
                            for (int col = 0; col < nSize; col++) {
                                if (sudoku[row+startRow][col+startCol] != k) {
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
        
    }
}