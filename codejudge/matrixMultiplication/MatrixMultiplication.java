import java.util.Scanner;

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read size and order of matrix
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        String order = in.next(); // C => row-major & F => column-major

        // Init and fill arrays
        int AElements = n*m;
        int BElements = m*p;
        int CElements = n*p;
        int[] A = new int[AElements];
        int[] B = new int[BElements];

        for (int i = 0; i < A.length; i++) {
            A[i] = in.nextInt();
        }
        for (int i = 0; i < B.length; i++) {
            B[i] = in.nextInt();
        }

        // Matrix multiplication
        int[] C = new int[CElements];
        if (order.equals("C")) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < p; j++) { 
                    for (int k = 0; k < m; k++) {
                        C[i*p+j] = C[i*p+j] + (A[i*m+k]*B[k*p+j]);
                    }
                }
            }
        }
        else if(order.equals("F")) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < p; j++) { 
                    for (int k = 0; k < m; k++) {
                        C[j*n+i] = C[j*n+i] + (A[k*n+i]*B[j*m+k]);
                    }
                }
            }
        }
        else {
            System.out.println("Invalid order"); 
        }

        for (int i = 0; i < C.length; i++) {
            System.out.println(C[i]);
        }
    }
}
