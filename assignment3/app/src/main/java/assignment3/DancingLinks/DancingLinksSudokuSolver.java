package assignment3.DancingLinks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DancingLinksSudokuSolver {

    public static void DLXSolver(int[][] sudoku, int nSize) {
        // 2. Converting sudoku to exact cover binary matrix
        int[][] exactCoverMatrix = SudokuToExactCover.reductionToExactCover(sudoku, nSize);

        // 3. Construct dancing links matrix from exact cover matrix and save root ColumnNode in variable h
        ColumnNode h = DancingLinksMatrix.createDancingLinks(exactCoverMatrix);

        // 4. Applying DLX and saving DLX result as list of list
        List<Node> S = new ArrayList<>();
        List<List<Integer>> DLX = DLXSearch.search(h, S);

        // 5. Decode DLX back into solved sudoku solution
        ExactCoverToSudoku.exactCoverToSudoku(nSize, DLX);

    }
    public static void main(String[] args) {
        // 1. Reading the sudoku as input
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

        DLXSolver(sudoku,nSize);
    }
}

/* 
2
0 0 0 3
0 4 0 0
0 1 0 0
0 3 2 0
 */