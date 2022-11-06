package assignment3.DancingLinks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DancingLinksMatrix {
    public static ColumnNode createDancingLinks(int[][] binaryMatrix) {
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
                    // dancingLinksMatrix.get(i).add(n);
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
        
        return root;
    }

    // Printing column names associated to all nodes 
   /*  public static void printDacingLinks(List<List<Node>> dancingLinks) {
        for (int i = 0; i < dancingLinks.size(); i++) {
            for (int j = 0; j < dancingLinks.get(i).size(); j++) {
                System.out.print(dancingLinks.get(i).get(j).column.name + " ");
            }
            System.out.println();
        }
    } */

    // Printing column names associated to all nodes  
    public static void printDacingLinks(ColumnNode h) {
        // Using a HashSet to see if Node has been printed already
        Set<Node> printedNodes = new HashSet<>();
        // iterate over column nodes
        for (ColumnNode j = (ColumnNode)h.right; j != h; j=(ColumnNode)j.right) {
            // iterate over nodes associated to column nodes (down)
            for (Node i = j.down; i != j; i=i.down) {
                // Checking if node has been printed already
                if (!printedNodes.contains(i)) {
                    // Printing all nodes on the row and adding them to HashSet
                    for (Node n = i; !printedNodes.contains(n); n=n.right) {
                        printedNodes.add(n);
                        System.out.print(n.column.name);
                    }
                    System.out.println();
                }
            }
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
