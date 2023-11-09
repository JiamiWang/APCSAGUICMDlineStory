package net.spicycombo.jiamingwang.storyproj;

import java.util.Map;

public class Terminal {
    private boolean enabled = true;
    public boolean useColors = false;
    
    public boolean DisplayScene(Scene scene) {
        if (!enabled) return false;
        
        if (Main.story.isEndScene(scene)) {
            print("&yYou reached the end of the game! &wScenario " + scene.getSceneId());
            print("&w" + scene.getScene());
            print("&gThank you for playing! :D");
        }
        else {
            String sceneMsg = "&bNow at Scene &y" + scene.getSceneId();
            print(paddedLine('*', Misc.sanitize(sceneMsg).length()));
            print(sceneMsg);
            print(paddedLine('*', Misc.sanitize(sceneMsg).length()));
            print("&g" + scene.getScene());
            print("&bYour options are (type the text EXACTLY as before the vertical bar):");
            Map<String, String> options = scene.getOptions();
            for (String k : options.keySet()) {
                print("&w" + k + " &b| &w" + options.get(k));
            }
        }
        
        return true;
    }
    
    public boolean getStatus() {
        return enabled;
    }

    public void setStatus(boolean newStat) {
        enabled = newStat;
    }

    public Terminal() {
        
    }

    public void IntroductCmdLine() {
        if (!enabled) {
            System.out.println("Terminal not enabled. Use the Graphical Interface instead.");
            System.out.println("Warn: If this terminal window is closed, the GUI will terminate.");
            Main.story.UpdateInterfaces();
            return;
        }

        print("&bInfo: &wThis is the terminal interface.");
        print("&bInfo: &wFor available commands, type /help");
        if (Main.instanceGUI != null && Main.instanceGUI.getStatus()) {
            print("&rNotice: &wGUI is enabled. Interfacing with either this terminal, or the graphic window will change your progression in the story. Closing either this terminal, or the GUI will terminate the program.");
        }

        Main.story.UpdateInterfaces();
    }

    public void ReadLoop() {
        if (!enabled) while (true) {
            String input = Inputs.s.nextLine();
            
            if (Inputs.caselessEq(input, "amogus")) {
                System.out.println("AMONG US!");
            }
        }
        
        else
        while (true) {
            String input = Inputs.s.nextLine();
            if (input.startsWith("/")) {
                ExecCommand(input);
            } else {
                boolean validatedOption = Main.story.ValidateOption(input);
                
                if (Main.story.getIsEnded()) print("&r!! The story ended, story no longer accepts input.");
                else if (!validatedOption) print("&r!! Not a valid option in the story line.");
            }
        }
    }

    // TODO: Create command classes, and implement object inheritance
    public void ExecCommand(String input) {
        String[] args = input.split("\\s+"); 
        if (Inputs.caselessEq(args[0], "/help")) {
            cmdPrint("&b/help&y: Displays this menu.");
            cmdPrint("&b/music&y: Toggle on or off the background music.");
            cmdPrint("&b/status&y: Displays current program status.");
            cmdPrint("&b/about&y: About this program.");
            //cmdPrint("&b/manipulate [type] [text]&y: Change text in a component.");
            cmdPrint("&b/exit&y: Quits the program.");
        }
        else if (Inputs.caselessEq(args[0], "/exit")) {
            cmdPrint("&rExiting...");
            System.exit(0);
        } else if (Inputs.caselessEq(args[0], "/music")) {
            if (Main.bMusic != null) Main.bMusic.toggle();

            cmdPrint("The music is now " +  (Main.bMusic.isPlaying() ? "&gon^r" : "&roff^r") + ".");
            if (Main.bMusic.isPlaying()) cmdPrint("I'd hope for you to enjoy the music!");
            else {
                cmdPrint("Thank you for listening! Tracks are made by Ozzed!");
                cmdPrint("Visit Ozzed at https://www.ozzed.net");
            }
        }
        // Developer only command
        /*
        else if (Inputs.caselessEq(args[0], "/manipulate")) {
            if (3 > args.length) { cmdPrintn("&cNot enough arguments!"); return; }
            
            if (Inputs.caselessEq(args[1], "jtextarea")) {
                if (Main.instanceGUI != null) Main.instanceGUI.changeJTextAreaContent(
                    input.substring(input.indexOf(args[2]))
                );
            }
            else {
                cmdPrint("&cNot a valid component type!");
            }
        }*/   
        else if (Inputs.caselessEq(args[0], "/status")) {
            cmdPrint("You are currently at scene with ID " + Main.story.curScene.getSceneId());
            if (Main.bMusic != null) cmdPrint("Currently, the music player is " + (Main.bMusic.isPlaying() ? "&gon" : "&roff") + ".");
            else cmdPrint("Music player isn't instantiated, can't fetch status");
        }
        else if (Inputs.caselessEq(args[0], "/about")) {
            cmdPrint("&bAbout this program:");
            cmdPrint("&b Program: " + Misc.softwareName);
            cmdPrint("&b Author: " + Misc.creator);
            cmdPrint("&b Credits:");
            for (String line : Misc.creditLines) {
                cmdPrint("&y  " + line);
            }
            cmdPrint("&b This project is licensed under MIT.");
        }
        else {
            cmdPrint("&rUnknown command. &wType /help for available commands.");
        }
    }

    private void cmdPrint(String input) {
        print("&g[/] &w" + input);
    }

    // TODO: Add coloring strings, and truncate tokens when coloring is disabled
    public void print(String str) {
        if (useColors) System.out.println(colorize(str + "^r"));
        else System.out.println(Misc.sanitize(str));
    }

    // Static, public tools to use outside of instance
    
    public static String paddedLine(char c, int length) {
        return String.format("%"+length+"s", "").replace(' ', c);
    }

    // TODO: Function should truncate Color Tokens when color displaying is not enabled.
    // public static String truncateColorTokens(String text) {
    //     String curString = text;
    // }
    
    // TODO: Replace color tokens with ANSI codes
    public static String colorize(String text) {
        return text
            .replace("&z", ANSI.BLACK)
            .replace("&r", ANSI.RED)
            .replace("&g", ANSI.GREEN)
            .replace("&y", ANSI.YELLOW)
            .replace("&b", ANSI.BLUE)
            .replace("&p", ANSI.PURPLE)
            .replace("&c", ANSI.CYAN)
            .replace("&w", ANSI.WHITE)

            .replace("&Z", ANSI.BLACK_BACKGROUND)
            .replace("&R", ANSI.RED_BACKGROUND)
            .replace("&G", ANSI.GREEN_BACKGROUND)
            .replace("&Y", ANSI.YELLOW_BACKGROUND)
            .replace("&B", ANSI.BLUE_BACKGROUND)
            .replace("&P", ANSI.PURPLE_BACKGROUND)
            .replace("&C", ANSI.CYAN_BACKGROUND)
            .replace("&W", ANSI.WHITE_BACKGROUND)
            
            .replace("^r", ANSI.RESET)
            .replace("^b", ANSI.BOLD)
            .replace("^u", ANSI.UNDERLINE)
            .replace("^B", ANSI.BLINK)
            .replace("^i", ANSI.INVERT)
            .replace("^h", ANSI.HIDDEN)
            .replace("^s", ANSI.STRIKETHROUGH);
    }
}
