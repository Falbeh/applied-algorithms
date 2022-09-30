package Rho;

import java.util.Scanner;

public class Rho {
    public static void main(String[] args) {
         // Taking input
         Scanner in = new Scanner(System.in);

         while (in.hasNextLine()) {
            int input = Integer.parseUnsignedInt(in.nextLine(), 16); // D -> 13 (1101)
            
            // Finding first 1 bit from left
            int firstOneBit = 1;

            // Arithmetic right shift until 1 is found starting from left-most bit
            for (int i = 32; i > 0; i--) {
                if ((input>>i-1 & 1) == 1) {
                    break;
                }
                firstOneBit++;
            }
            System.out.println(firstOneBit);
         }
         in.close();
    }
}
