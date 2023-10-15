import java.util.Scanner;;

public class Inputs {
    public static Scanner s;
    
    public static boolean caselessEq(String str, String str2) {
        return str.toLowerCase().equals(str2.toLowerCase());
    }
    
    public static boolean getBoolean() {
        while (true) {
            String userInput = s.nextLine();

            if (caselessEq(userInput, "true") || caselessEq(userInput, "yes")) return true;
            else if (caselessEq(userInput, "false") || caselessEq(userInput, "no")) return false;
            else System.out.println("!! Not valid input. Type yes or no!");
        }
    }
}