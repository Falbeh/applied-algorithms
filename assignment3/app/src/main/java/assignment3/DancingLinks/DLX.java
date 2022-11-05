package assignment3.DancingLinks;

import java.util.ArrayList;
import java.util.List;

public class DLX {

    // Parameters are ColumnNode (root) and the stack S
    public static void search(ColumnNode h, List<Node> S) {
        // If root node is alone in columns then finish
        if (h.right == h) {
            ProcedureDLX.print(S); // Printing
            // Need to terminate here
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
                // Recursive call
                search(h, S);

                // Going back 
                S.remove(S.size()-1);
                c = r.column;

                for (Node j = r.left; j != r; j=j.left) {
                    ProcedureDLX.unCover(j.column);
                }
            }
            ProcedureDLX.unCover(c);
        }
    }

    public static void main(String[] args) {
        // Storing the answer S
        List<Node> S = new ArrayList<>();

        // Call Sudoku to exact cover

        // Call DancingLinksMatrix
        ColumnNode h = DancingLinksMatrix.createDancingLinks(new int[][] {
           /*  {1,0,1},
            {1,1,0},
            {0,1,0} */
            {0, 0, 1, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 1}
        });

        search(h, S);
    }
}
