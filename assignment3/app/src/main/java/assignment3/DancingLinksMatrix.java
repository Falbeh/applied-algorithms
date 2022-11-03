package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DancingLinksMatrix {
    public static int[][] createDancingLinks(int[][] binaryMatrix) {
        int columnCount = binaryMatrix[0].length;
        // Making root node
        ColumnNode root = new ColumnNode("root");
        List<ColumnNode> columnNodes = new ArrayList<>();
        
        // For each column in binaryMatrix make a column node header and hook it to the right of the previous
        ColumnNode temp=root;
        for (int i = 1; i <= columnCount; i++) {
            ColumnNode cn = new ColumnNode((String.valueOf(i)));
            // Adding to list so we can connect ordinary nodes by getting index later
            columnNodes.add(cn);
            temp.hookright(cn);
            temp = cn;
        }
      
        // Add links from binary matrix
        for (int i = 0; i < binaryMatrix.length; i++) {
            Node prev = null;
            for (int j = 0; j < columnCount; j++) {
                if (binaryMatrix[i][j] == 1) {
                    ColumnNode cn = columnNodes.get(j);
                    Node n = new Node(cn);
                    cn.size++;
                    
                    // Check if there have been a previous node in this row 
                    if (prev == null) {
                        prev = n;
                    }
                    // Link column header up Node to the new Node n (first time this will be itself)
                    cn.up.hookDown(n);

                    // Link node to other node in row
                    prev.hookright(n);
                    prev = n;
                }
            }
        }

        // Set temp back to root
        temp=root;

        for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(temp.name);
            }
        }
        
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int FLength = in.nextInt();
        int ULength = in.nextInt();
        
        // Reading the input which is a binary matrix
        int[][] binaryMatrix = new int[FLength][ULength];

        for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < binaryMatrix[0].length; j++) {
                binaryMatrix[i][j] = in.nextInt(); 
            }
        }

        /* for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < binaryMatrix[0].length; j++) {
                System.out.print(binaryMatrix[i][j]);
            }
            System.out.println();
        } */

        createDancingLinks(binaryMatrix);

    }
}
