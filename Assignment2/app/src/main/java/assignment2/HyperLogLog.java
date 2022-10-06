package assignment2;

import java.util.ArrayList;
import java.util.List;

public class HyperLogLog {

    // Rho method from Assignment2/Rho.java
    public static int rho(String input) {
        int inputRead = Integer.parseUnsignedInt(input, 16);
            
        // Finding first 1 bit from left
        int firstOneBit = 1;

        // Arithmetic right shift until 1 is found starting from left-most bit
        for (int i = 32; i > 0; i--) {
            if ((inputRead>>i-1 & 1) == 1) {
                break;
            }
            firstOneBit++;
        }
        return firstOneBit;

    }

    // Hashing 
    public static int f(int x) {
        return x = ((x*0xbc164501) & 0x7fffffff) >> 21;
    }

    // Hash method from Assignment2/Hash.java
    public static String hashing(int x) {
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

        // Getting array bits as String of bits reversed
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
        return hexString;

    } 

    public static double sumM(int[] M) {
        double sum = 0;
        for (int i = 0; i < M.length; i++) { 
            sum += Math.pow(2, -M[i]);
        }
        return sum;
    }

    // HyperLogLog method
    public static void hyper(List<Integer> Y, int m) {

        // calculate alpha m
        double alpha = 0.7213/(1 + 1.079/m);
        int[] M = new int[m-1];

        // initialize registers
        for (int i = 0; i < m; i++) {
            M[i] = 0;
        }

        // Loop through input stream
        for (Integer y : Y) {
            // Hash function f selects the register
            int j = f(y);
            // Hash function h is used for ρ computations
            String x = hashing(y);
            // Updating to max by checking if rho(x) is bigger else keep value
            M[j] = Math.max(M[j], rho(x));
        }

        // Calculating nHat
        double nHat = alpha*(m*m)*(Math.pow(sumM(M), -1));

        // Number of empty registers 
        
    }

    public static void main(String[] args) { 

        // Input stream
        List<Integer> stream = new ArrayList<>();  
        for (int i = 1000000; i < 2000000; i++) {
            stream.add(i);
        }
        
        // Call hyperLogLog
        hyper(stream, 1024);
        
    }       
}

