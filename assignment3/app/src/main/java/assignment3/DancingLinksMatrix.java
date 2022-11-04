package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DancingLinksMatrix {
    public static List<List<Node>> createDancingLinks(int[][] binaryMatrix) {
        int rowCount = binaryMatrix.length;
        int columnCount = binaryMatrix[0].length;
        // Making root node
        ColumnNode root = new ColumnNode("root");
        List<ColumnNode> columnNodes = new ArrayList<>();
        List<List<Node>> dancingLinksMatrix = new ArrayList<>();
        
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
            dancingLinksMatrix.add(new ArrayList<Node>());
            Node prev = null;
            for (int j = 0; j < columnCount; j++) {
                if (binaryMatrix[i][j] == 1) {
                    // System.out.println(j + columnNodes.get(j).name);
                    ColumnNode cn = columnNodes.get(j);
                    Node n = new Node(cn);
                    dancingLinksMatrix.get(i).add(n);
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
        
        return dancingLinksMatrix;
    }

    // Printing column names associated to all nodes 
    public static void printDacingLinks(List<List<Node>> dancingLinks) {
        for (int i = 0; i < dancingLinks.size(); i++) {
            for (int j = 0; j < dancingLinks.get(i).size(); j++) {
                System.out.print(dancingLinks.get(i).get(j).column.name + " ");
            }
            System.out.println();
        }
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

        // Calling methods
        printDacingLinks(createDancingLinks(binaryMatrix));

    }
}
 
/** EXAMPLE INPUT 

6 7
0 0 1 0 1 1 0
1 0 0 1 0 0 1
0 1 1 0 0 1 0
1 0 0 1 0 0 0
0 1 0 0 0 0 1
0 0 0 1 1 0 1

*/
