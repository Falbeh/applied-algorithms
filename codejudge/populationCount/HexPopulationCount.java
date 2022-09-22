import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HexPopulationCount {
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
        List<Long> hexList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            hexList.add(Long.parseUnsignedLong(in.next(), 16));
        }

        for (long l : hexList) {
            System.out.println(countPopulation(l));
        }
    }
}
