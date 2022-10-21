import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);

        // Reading first line eg: p cnf 4 3
        // String firstLine = in.readLine();
        String firstLine = input.nextLine();

        // Splitting and parsing variables and clauses to int
        String[] split = firstLine.split(" ");
        int Nvariables = Integer.parseInt(split[2]);
        int Nclauses = Integer.parseInt(split[3]);

        // Defining list with clauses
        List<List<Integer>> clauses = new ArrayList<>();
       
        // Adding to lists
        for (int i = 0; i < Nclauses; i++) {
            List<Integer> clause = new ArrayList<>();
            while(true) {
                int nextInt = input.nextInt();
                if (nextInt == 0) {  
                    clauses.add(clause);
                    break;
                }
                else {
                    clause.add(nextInt);
                }
            }
        }

        // Makes a map with keys being variables from truth assignment and value true/false 
        Map<Integer, Boolean> truthAssignment = new HashMap<>();

        input.next();
        for (int i = 0; i < Nvariables; i++) {
            int nextint = input.nextInt();
            if (nextint < 0) {
                truthAssignment.put(nextint, false);
            }
            else {
                truthAssignment.put(nextint, true);
            }
        }

        // Checking if truth assignment is okay for each clause list in clauses check if one of the truthassignment int are there
        boolean satisfiable = true;
        for (List<Integer> list : clauses) {
            if (!satisfiable) {
                break;
            }
            else {
                for (Integer i : list) {
                    if (i > 0 && truthAssignment.containsKey(i)) {
                        satisfiable=true;
                        break;
                    }
                    else if (i < 0 && truthAssignment.containsKey(i)) {
                        satisfiable=true;
                        break;
                    }
                    else {
                        satisfiable=false;
                    }
                }
            }
            
        }
        if (satisfiable) {
            System.out.println("TRUE");
        }
        else {
            System.out.println("FALSE");
        }
    }
}