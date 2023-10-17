/*
   _____ __                              
  / ___// /_____  _______  __            
  \__ \/ __/ __ \/ ___/ / / /            
 ___/ / /_/ /_/ / /  / /_/ /             
/____/\__/\____/_/   \__, /           __ 
      / __ \________/____(_)__  _____/ /_
     / /_/ / ___/ __ \  / / _ \/ ___/ __/
    / ____/ /  / /_/ / / /  __/ /__/ /_  
   /_/   /_/   \____/_/ /\___/\___/\__/  
                   /___/                 

    Story Project by Jiaming Wang, Period 2
*/

import java.util.Scanner;;

public class Main {
    static GUI instanceGUI = null; 
    static Terminal instanceTerminal = null;

    static Story story = null;
    // set to null, avoid undefined vars completely

    public static void StartupInit() {
        System.out.println(Misc.welcomeASCII);
        String welcomeText = Misc.softwareName + " by " + Misc.creator;
        System.out.println(Terminal.paddedLine('=', welcomeText.length()));
        System.out.println(welcomeText);
        System.out.println(Terminal.paddedLine('=', welcomeText.length()));
        
        Inputs.s = new Scanner(System.in);
        instanceTerminal = new Terminal();
    }
    
    public static void InitByUserInput() {
        System.out.print("\nEnable graphical interface? [yes/no] ");
        boolean yesToGUI = Inputs.getBoolean();

        System.out.print("\nContinue using terminal interface? [yes/no] ");

        boolean yesToCmd = Inputs.getBoolean();

        if (!yesToGUI && !yesToCmd) { // !(yesToGUI || yesToCmd)
            System.out.println("You've chosen to not enable any of the interfaces. Exiting...");
            Misc.PressAnyKey(0);
        }

        boolean yesToColors = false;
        
        if (yesToCmd) {
            System.out.print("\nUse colors on terminal interface? [yes/no] ");
            yesToColors = Inputs.getBoolean();
        }
        
        if (yesToGUI) instanceGUI = new GUI();
        instanceTerminal.setStatus(yesToCmd); // terminalGUI is already instantiated at this case
        instanceTerminal.useColors = yesToColors;
        
        story = new MyStory();
    }
    
    // Program entry point
    public static void main(String[] args) {
        StartupInit();
        InitByUserInput();

        System.out.println("\nProgram finished Initializing.");
        System.out.println("Proceeding to story...");

        instanceTerminal.ReadLoop();
    }
}
