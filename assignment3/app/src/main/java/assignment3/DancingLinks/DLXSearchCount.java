package assignment3.DancingLinks;

import java.util.ArrayList;
import java.util.List;

public class DLXSearchCount {
    // List to save all solutions in
    private List<List<List<Integer>>> solutionBucket = new ArrayList<>();

    // Parameters are ColumnNode (root) and the stack S
    public int search(ColumnNode h, List<Node> S, int count) {
        if (h.right == h) {
            // If solution is found add it to the bucket of solutions
            solutionBucket.add(ProcedureDLX.print(S));
        }
        else {
            ColumnNode c = ProcedureDLX.choose(h);
            
            ProcedureDLX.cover(c);
            // Iterate over rows that have a one on column c
            for (Node r = c.down; r != c; r=r.down) {
                S.add(r);
                // Cover the columns covered by row r
                for (Node j = r.right; j != r; j=j.right) {
                    ProcedureDLX.cover(j.column);
                }
                // Recursive call returning first possible result
                search(h, S, count);
            
                // Going back 
                S.remove(S.size()-1);
                c = r.column;

                for (Node j = r.left; j != r; j=j.left) {
                    ProcedureDLX.unCover(j.column);
                }
            }
            ProcedureDLX.unCover(c);
        }
        // Finally return the solution bucket size
        return solutionBucket.size();
    }

    public static void main(String[] args) {
        // Storing the answer S
        List<Node> S = new ArrayList<>();

        // Call Sudoku to exact cover

        // Call DancingLinksMatrix
        ColumnNode h = DancingLinksMatrix.createDancingLinks(new int[][] {
            {1,0,1},
            {1,1,0},
            {0,1,0},
            {0,0,1}
            /* {0, 0, 1, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 1} */
        });

        DLXSearchCount dlxsc = new DLXSearchCount();

        int count = dlxsc.search(h, S, 0);

        System.out.println(count);
    }
}
