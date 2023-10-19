public class Terminal {
    private boolean enabled = true;
    public boolean useColors = false;
    
    public boolean getStatus() {
        return enabled;
    }

    public void setStatus(boolean newStat) {
        enabled = newStat;
    }

    public Terminal() {
        
    }

    public void ReadLoop() {
        if (!enabled) {
            System.out.println("Terminal not enabled. Use the Graphical Interface instead.");
            System.out.println("Warn: If this terminal window is closed, the GUI will terminate.");
            return;
        }

        print("&bInfo: &wThis is the terminal interface.");
        print("&bInfo: &wFor available commands, type /help");
        if (Main.instanceGUI != null && Main.instanceGUI.getStatus()) {
            print("&rNotice: &wGUI is enabled. Interfacing with either this terminal, or the graphic window will change your progression in the story. Closing either this terminal, or the GUI will terminate the program.");
        }
        
        while (true) {
            String input = Inputs.s.nextLine();
            if (input.startsWith("/")) {
                ExecCommand(input);
            } else {
                boolean validatedOption = Main.story.ValidateOption(input);
                
                if (!validatedOption) print("&r!! Not a valid option in the story line.");
            }
        }
    }

    // TODO: Create command classes, and implement object inheritance
    public void ExecCommand(String input) {
        String[] args = input.split("\\s+"); 
        if (Inputs.caselessEq(args[0], "/help")) {
            cmdPrint("&b/help&y: Displays this menu.");
            cmdPrint("&b/status&y: Displays current story progress.");
            cmdPrint("&b/about&y: About this program.");
            cmdPrint("&b/exit&y: Quits the program.");
        }
        else if (Inputs.caselessEq(args[0], "/exit")) {
            cmdPrint("&rExiting...");
            System.exit(0);
        }
        else if (Inputs.caselessEq(args[0], "/about")) {
            cmdPrint("&bAbout this program:");
            cmdPrint("&b Program: " + Misc.softwareName);
            cmdPrint("&b Author: " + Misc.creator);
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
            .replace("&b", ANSI.BLACK)
            .replace("&r", ANSI.RED)
            .replace("&g", ANSI.GREEN)
            .replace("&y", ANSI.YELLOW)
            .replace("&b", ANSI.BLUE)
            .replace("&p", ANSI.PURPLE)
            .replace("&c", ANSI.CYAN)
            .replace("&w", ANSI.WHITE)

            .replace("&B", ANSI.BLACK_BACKGROUND)
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