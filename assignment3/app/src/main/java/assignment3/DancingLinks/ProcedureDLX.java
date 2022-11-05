package assignment3.DancingLinks;

import java.util.ArrayList;
import java.util.List;

public class ProcedureDLX {

    // Choosing the column with smallest size 
    public static ColumnNode choose(ColumnNode h) {
        // Using -1 as the first size instead of infinity
        int s = -1;
        ColumnNode c = h;
        for (ColumnNode j = (ColumnNode)h.right; j != h; j=(ColumnNode)j.right) {
            if (j.size < s || s == -1) {
                c = j;
                s = j.size;
            }
        }
       
        return c;
    }

    public static void cover(ColumnNode cn) {
        // Unlink column header from the list of column headers
        cn.unlinkLeftRight();
        
        // Iterate over rows and cols and cover
        for (Node i = cn.down; i != cn; i=i.down) {
            for (Node j = i.right; j != i; j=j.right) {
                j.unlinkUpDown();
                j.column.size--;
            }
        }
    }

    public static void unCover(ColumnNode cn) {
        // Iterate over rows and cols and unCover
        for (Node i = cn.up; i != cn; i=i.up) {
            for (Node j = i.left; j != i; j=j.right) {
                j.column.size++;
                j.linkUpDown();
            }
        }
        // Link column header to the list of column headers
        cn.linkLeftRight();
    }

    public static void print(List<Node> S) {

        // List<List<Node>> result = new ArrayList<>();
        List<Node> Sresult = new ArrayList<>();
        for (int i = 0; i < S.size(); i++) {
            Sresult.add(S.get(i));
        }

        // Removing the element at the last index of the list
        while (!Sresult.isEmpty()) {
            // System.out.println("hm");
            Node n = Sresult.remove(Sresult.size()-1); 
            Node j = n;
            do {
                System.out.print(j.column.name);
                j = j.right;
            }while(j!=n);
            System.out.println();
        }
        // return result;
    }
}
