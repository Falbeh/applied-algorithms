import java.util.Scanner;

public class MatrixElement {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        // Read size and order of matrix
        int n = in.nextInt();
        int m = in.nextInt();
        String order = in.next(); // C => row-major & F => column-major

        // Init and fill array 
        int nElements = n*m;
        int[] data = new int[nElements];

        for (int i = 0; i < nElements; i++) {
            data[i] = (in.nextInt());
        }

        // Print said elements
        while (in.hasNext()) {
            int i = in.nextInt();
            int j = in.nextInt();

            if (order.equals("C")) {
                System.out.println(data[i*m+j]);
            }
            else if(order.equals("F")) {
                System.out.println(data[j*n+i]);
            }
            else {
                System.out.println("Invalid order"); 
            }
        }

        in.close();
    }
}

/* Input example:
2 2 C
1 2 3 4
0 0
0 1
1 0
1 1 
*/ 


