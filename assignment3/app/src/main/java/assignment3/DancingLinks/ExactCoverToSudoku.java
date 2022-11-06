package assignment3.DancingLinks;

import java.util.ArrayList;
import java.util.List;

public class ExactCoverToSudoku {
    public static int[][] exactCoverToSudoku(int n, List<List<Integer>> DLX) {
        int size = n*n;
        int[][] result = new int[size][size];
        

        for (int i = 0; i < DLX.size(); i++) {
            int rNum = DLX.get(i).get(0);
            int cNum = DLX.get(i).get(1);

            int row = (int)Math.ceil((double)rNum / (double)size)-1;
            
            int col = cNum-(int)Math.pow(n,4);
            col = (int)Math.ceil((double)col / (double)size)-1;
           
            //System.out.println("row: "+(row+1)+ " col: " +(col+1));
            int value = (cNum % size);
            if (value == 0) {
                value=size;
            }
            // System.out.println(value);
            result[row][col] = value;
        }
        
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        return result;
    }
    public static void main(String[] args) {
        exactCoverToSudoku(2, new ArrayList<>());
    }
}
