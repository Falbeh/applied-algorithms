/*
 * This Java source file was generated by the Gradle 'init' task.
*/

// To run: java -jar app/build/libs/app.jar

package evaluatecnf;

import java.io.FileWriter;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) throws Exception {
        // Generate cnf file from input

        FileWriter cnfGen = new FileWriter("cnf/cnfgen.cnf");
        Scanner in = new Scanner(System.in);

        // Writing first line of cnf file
        cnfGen.write(in.next());
        cnfGen.write(" ");
        cnfGen.write(in.next());

        int nVariables = in.nextInt();
        int mClauses = in.nextInt();

        cnfGen.write(" " + nVariables + " " + mClauses);
        cnfGen.write(System.getProperty( "line.separator" ));

        for (int i = 0; i < mClauses; i++) {
            while(true) {
                int nextInt = in.nextInt();
                if (nextInt == 0) {
                    cnfGen.write(Integer.toString(nextInt));
                    if (i != mClauses-1) {
                        cnfGen.write(System.getProperty( "line.separator" ));
                    }
                    break;
                }
                else {
                    cnfGen.write(nextInt + " ");
                }
            }
        }
            
        in.close();
        cnfGen.close();
    }
}
