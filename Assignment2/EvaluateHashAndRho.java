import java.util.Scanner;

public class EvaluateHashAndRho {

    public static int[] readData() {
        Scanner s = new Scanner(System.in);
        int[] x = null;
        try {
            int n = s.nextInt();
            x = new int[n];
            for (int i = 0; i < n; ++i) {
                x[i] = s.nextInt();
            }
        } finally {
            s.close();
        }
        return x;
    }
    
    public static void main(String[] args) {
        int[] x = readData();
        int[] y = null;

    }
}