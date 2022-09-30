package JenkinsOneAtATimeHash;

import java.util.Scanner;

public class Main {

    public static void JenkinsOneAtATimeHash(String s, int length) {
        int hash = 0;
        int i = 0;
        while (i != length) {
            // Getting the char at i's place, example: a = 97
            hash = hash + s.charAt(i);
            hash = hash + (hash << 10);
            hash = hash ^ (hash >>> 6);
            i++;
        }
        hash = hash + (hash << 3);
        hash = hash ^ (hash >>> 11);
        hash = hash + (hash << 15);
        System.out.println(Integer.toHexString(hash));
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();
        
        JenkinsOneAtATimeHash(input, input.length());      
    }
}
