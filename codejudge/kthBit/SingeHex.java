import java.util.Scanner;

public class SingeHex {

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
        Scanner in = new Scanner(System.in);

        int pos = in.nextInt();
        int hex = Integer.parseUnsignedInt(in.next(), 16);
        int newHex = rightShift(hex, pos);

        System.out.println(checkBit(newHex)); 
    }
}
