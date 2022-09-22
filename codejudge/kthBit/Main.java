import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Your program should read a number of unsigned integers from standard input,
// extract the kth bit in each, and print out the values of the said bits (one per line). 
// The first line of the input contains two integers: nn and k separated by spaces. 
// The number n is equal to the number of integers that follow, and 0 \leq k \leq 310≤k≤31 is the number of the bit to extract. 
// The remaining n lines each contain an unsigned 32-bit integer as a 8-byte hex string. 
// Your program should output n lines each containing either a character '1' or a character '0' (without the apostrophes).

// test input:
// 3 5
// 27f4a17b
// fa1ab344
// 3aafbd17

// output: 
// 1
// 0
// 0


public class Main {

    public static int rightShift(int hex, int k) {
        return (hex >> k);
    }

    public static int checkBit(int hex) {
        if ((hex & 00000001) == 00000001) {
            return 1;
        }
        else {
            return 0;
        }
    }


    public static void main(String[] args) {
        List<Integer> hexList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        // Number of hex 
        int n = in.nextInt();
        // kth position to retrieve
        int pos = in.nextInt();

        // Adding hex numbers to list
        for (int i = 0; i < n; i++) {
            hexList.add(Integer.parseUnsignedInt(in.next(), 16));
        }
        in.close();

        for (Integer i : hexList) {
            int newHex = rightShift(i, pos);
            System.out.println(checkBit(newHex)); 
        }
    }
}
