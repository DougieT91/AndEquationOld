package andequation;

import files.FileIOImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmuringani on 7/7/17.
 */
public class AndEquationMain {
    private static int Y;

    public static void main(String[] args){
        // Fields
        String inputFile="./files/test2.txt",outputFile="./files/output/test2-output.txt";
        List<String> inputList= new ArrayList<>();              //String ArrayList

        // Check for the right number of arguments
        if(args.length==2){
            inputFile=args[0];      // The <input-file> string
            outputFile=args[1];     // The <output-file> string
        }else{
            System.out.println("Please run program with 2 arguments (input and output) in the format: java -jar AndEquation.jar <input.txt> <output.txt>");
            System.out.println("\nProgram Exiting...");
            System.exit(1);
        }


        //Read the File
        try {
            inputList = new FileIOImpl().readFile(inputFile);
        } catch (Exception e) {
            System.out.println("Error occurred while trying to read file: " + e.getMessage());
            System.out.println("\nProgram Exiting...");
            System.exit(1);
        }

        // Convert/Split the single string (input) into String Array
        String[] stringArray=new FileIOImpl().splitString(inputList.get(0));


        //Convert the String Array to integer Array
        int[] intElements = StringToIntArr(stringArray);


        //Check Validity of Array against constraints specified. If any violations occur, exit the program
        boolean legal = new FileIOImpl().validateInput(intElements);
        if(legal==false){
            System.out.println("Constrains have been violated. \nThe program will now exit...");
            System.exit(1);
        }
        else{      // Else continue to perform the AND Equation and write the result in a text file
            Y = solveEquation(intElements);
            new FileIOImpl().writeFile(outputFile,String.valueOf(Y));
        }

        System.out.println("AND-Equation result is " + Y);
    }

    public static int[] StringToIntArr(String[] StrArr){
        int[] intArr= new int[StrArr.length];

        try{
            for(int j=0;j<StrArr.length;j++){
                String curr = StrArr[j];
                intArr[j]=Integer.parseInt(curr.trim());
            }
        }catch (NumberFormatException n){
            System.out.println("The input provided contains non-integer values. Make sure the input only contains numbers (and/or commas and curly braces)");
        }

        return intArr;
    }

    public static int solveEquation(int[] A) {
        int Y=A[0];

        for(int current:A){
            Y=Y&current;
        }

        return Y;
    }
}
