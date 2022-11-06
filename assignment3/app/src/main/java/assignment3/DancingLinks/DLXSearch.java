package assignment3.DancingLinks;

import java.util.ArrayList;
import java.util.List;

public class DLXSearch {

    // Parameters are ColumnNode (root) and the stack S
    public static List<List<Integer>> search(ColumnNode h, List<Node> S) {
        // If root node is alone in columns then finish
        if (h.right == h) {
            return ProcedureDLX.print(S); // returning List<List<Intger>> with result
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
                if (search(h, S).size() > 0) {
                    return ProcedureDLX.print(S);
                }

                // Going back 
                S.remove(S.size()-1);
                c = r.column;

                for (Node j = r.left; j != r; j=j.left) {
                    ProcedureDLX.unCover(j.column);
                }
            }
            ProcedureDLX.unCover(c);
        }
        // If there is no exact cover we return the empty list
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        // Storing the answer S
        List<Node> S = new ArrayList<>();

        // Call Sudoku to exact cover

        // Call DancingLinksMatrix

        ColumnNode h = DancingLinksMatrix.createDancingLinks(new int[][] {
            /* {1,0,1},
            {1,1,0},
            {0,1,0},
            {0,0,1} */
            {1,0,1},
            {1,1,0},
            {0,1,0} 
            /* {0, 0, 1, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 1} */
        });

        List<List<Integer>> result = search(h, S);

        for (List<Integer> l : result) {
            for (int i = 0; i < l.size(); i++) {
                System.out.print(l.get(i));
            }
            System.out.println();
        }
    }
}
