package Hash;

import java.util.Scanner;

public class Hash {

    // Method to reverse bits not used
    public static int reverseBits(int x) {
        int rev = 0;

        while (x > 0) {
            rev <<= 1;
            if ((x & 1) == 1) {
                rev ^= 1;
            }
            x >>= 1;
        }
        return rev;
    }

    public static void hashing(int x) {
        // Creating matrix A
        int A[] = new int[] {0x21ae4036, 0x32435171, 0xac3338cf, 0xea97b40c, 0x0e504b22, 0x9ff9a4ef, 0x111d014d, 0x934f3787, 
                                0x6cd079bf, 0x69db5c31, 0xdf3c28ed, 0x40daf2ad, 0x82a5891c, 0x4659c7b0, 0x73dc0ca8, 0xdad3aca2, 
                                0x00c74c7e, 0x9a2521e2, 0xf38eb6aa, 0x64711ab6, 0x5823150a, 0xd13a3a9a, 0x30a5aa04, 0x0fb9a1da, 
                                0xef785119, 0xc9f0b067, 0x1e7dde42, 0xdda4a7b2, 0x1a1c2640, 0x297c0633, 0x744edb48, 0x19adce93};
        
        // Multiplying with input on Matrix rows and adding them together
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.bitCount(A[i] & x);
            // mod 2
            A[i] = A[i] & 1;
            // System.out.println(A[i]);
        }

        // Getting array bits as String of bits
        String binaryString = "";
        for (int i = 31; i >= 0; i--) {
            binaryString = binaryString + A[i];
        }
        // System.out.println(binaryString);
        
        // Converting String of bits to hex
        long decimal = Long.parseLong(binaryString,2);
        String hexString = Long.toString(decimal,16);
        while (hexString.length() < 8) {
            hexString = "0" + hexString;
        }
        System.out.println(hexString);

    } 

    public static void main(String[] args) {
        
        // Taking input
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            int input = Integer.parseUnsignedInt(in.nextLine(), 16); // D -> 13 (1101)
     
            // Hashing
            hashing(input);
        }
        in.close();
    }
}
