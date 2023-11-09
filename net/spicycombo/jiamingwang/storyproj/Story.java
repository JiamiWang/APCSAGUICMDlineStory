  package net.spicycombo.jiamingwang.storyproj;

import java.util.*;

/*
    So, in this case I don't want to use an interface
     because in Java, an interface means that it is a 
     "clean" abstract class. Quite weird and quite 
     interesting compared to how C# will treat it.
*/

public abstract class Story {
    // Progress through story
    // Write Story

    public Scene curScene;
    public List<String> selections;
    
    private static Terminal cmd;
    private static GUI gui;
    
    private static boolean initialized;
    public static boolean started;
        
    public abstract void start();

    public static boolean Init(Terminal t, GUI g) {
        if (initialized) return false;
        cmd = t;
        gui = g;
        initialized = true;
        started = false;
        return true;
    } 
    
    public Story() {
        selections = new ArrayList<String>();
    }
    
    protected void UpdateInterfaces() {
        boolean doneCmd = false, doneGUI = false;
        
        if (gui != null) doneGUI = gui.DisplayScene(curScene);
        if (cmd != null) doneCmd = cmd.DisplayScene(curScene);
        
        if (!(doneCmd || doneGUI)) { System.out.println("!! no cmd, no gui? whoa"); } 
    }
    
    // Probably shouldn't make a String FUNCTION to 
    //  override just to get the name? idk
    protected abstract String name();

    // Returns the dialogue per scene based on the id
    //  of the scene. 
    // TODO: Change to use int instead maybe?
    protected abstract Scene getScene(String id);
    
    protected abstract boolean ValidateOption(String option);
    
    protected abstract boolean isEndScene(Scene s);
    
    protected abstract boolean getIsEnded();
}
