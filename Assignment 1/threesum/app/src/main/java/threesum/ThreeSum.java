/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package threesum;

import java.util.Arrays;
import java.util.HashMap;

public class ThreeSum {
    public String getGreeting() {
        return "Hello World!";
    }

    // Cubic solution
    public static int[] threeSumCubic(int[] x) {
        int n = x.length;
        for (int i = 0; i < n; ++i) {
            int a = x[i];
            for (int j = i + 1; j < n; ++j) {
                int b = x[j];
                for (int k = j + 1; k < n; ++k) {
                    int c = x[k];
                    if (a + b + c == 0) {
                        return new int[] { a, b, c };
                    }
                }
            }
        }
        return null;
    }

    // Quadratic solution
    public static int[] threeSumQuadratic(int[] x) {
        int n = x.length;
        int[] y = x.clone();
        Arrays.sort(y);
        for (int i = 0; i < n; ++i) {
            int a = y[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int b = y[left];
                int c = y[right];
                if (a + b + c == 0) {
                    return new int[] { a, b, c };
                } else if (a + b + c < 0) {
                    ++left;
                } else {
                    --right;
                }
            }
        }
        return null;
    }

    // Hashmap solution
    public static int[] threeSumHashMap(int[] x) {
        int n = x.length;
        HashMap<Integer, Integer> H = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; ++i) {
            H.put(x[i], i);
        }
        for (int i = 0; i < n; ++i) {
            int a = x[i];
            for (int j = i + 1; j < n; ++j) {
                int b = x[j];
                int c = -a - b;
                Integer k = H.get(c);
                if (k != null && j < k) {
                    return new int[] { a, b, c };
                }
            }
        }
        return null;
    }
    
    // Wrong Hashmap solution where && j < k is removed
    public static int[] threeSumHashMap1(int[] x) {
        int n = x.length;
        HashMap<Integer, Integer> H = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; ++i) {
            H.put(x[i], i);
        }
        for (int i = 0; i < n; ++i) {
            int a = x[i];
            for (int j = i + 1; j < n; ++j) {
                int b = x[j];
                int c = -a - b;
                Integer k = H.get(c);

                System.out.println("a: " + a);
                System.out.println("b: " + b);
                System.out.println("c: " + c);

                System.out.println("j: " + k);
                System.out.println("k: " + k);
                if (k != null) {
                    System.out.println(a + b + c);
                    return new int[] { a, b, c };
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSum().getGreeting());
        threeSumHashMap1(new int[] { 0, -2, 0 });
    }
}
