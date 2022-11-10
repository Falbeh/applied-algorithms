import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Submatrix {
    public static void main(String[] args) throws Exception{

        // Reading input and filling matrix
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(in.readLine());
        String submatrix = in.readLine();
        int i = Integer.parseInt(submatrix.split(" ")[0]);
        int j = Integer.parseInt(submatrix.split(" ")[1]);

        int[] matrix = new int[size*size];

        String numbers = in.readLine();

        for (int k = 0; k < matrix.length; k++) {
            matrix[k] = Integer.parseInt(numbers.split(" ")[k]);
        }

        // Find row and col of first element in given submatrix 
        int i_start = i*(size/2);
        int j_start = j*(size/2);

        // Getting submatrix in row-major order
        for (int k = 0; k < size/2; k++) {
            for (int k2 = 0; k2 < size/2; k2++) {
                // find next elements row and col
                int row = (i_start*size)+(k*size);
                int col = j_start+k2;
                
                System.out.print(matrix[row+col]);
                System.out.print(" ");
            }
        }
       

    }
}

