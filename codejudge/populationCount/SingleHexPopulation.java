import java.util.Scanner;

public class SingleHexPopulation {

    // Arithmetic right shift by 1
    public static long rightShift(long hex) {
        return (hex >> 1);
    }

    // Checks each bit of by right shifting 64 times (Probably a smart way to do it?)
    public static int countPopulation(long hex) {
        int count = 0;
        for (int i = 0; i < 64; i++) {
            if ((hex & 0000000000000001) == 0000000000000001) {
                ++count;
            }
            hex = rightShift(hex);
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long hex = Long.parseUnsignedLong(in.next(), 16);

        System.out.println(countPopulation(hex));
    }
}
