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

    public String curScene;
    public List<String> selections;
    
    private static Terminal cmd;
    private static GUI gui;
    
    private static boolean initialized;
    
    public boolean Init(Terminal t, GUI g) {
        if (initialized) return false;
        cmd = t;
        gui = g;
        initialized = true;
        return true;
    } 
    
    public Story() {
        selections = new ArrayList<String>();
    }
    
    private void UpdateInterfaces() {
        
    }
    
    // Probably shouldn't make a String FUNCTION to 
    //  override just to get the name? Idk
    public abstract String name();

    // Returns the dialogue per scene based on the id
    //  of the scene. 
    // TODO: Change to use int instead maybe?
    public abstract String getScene(String id);
    
    protected abstract boolean ValidateOption(String option);
    
    
}
