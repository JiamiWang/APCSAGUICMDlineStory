package net.spicycombo.jiamingwang.storyproj;

import java.util.*;

/**
 * Represents a scene in a story
 * 
 * @author Jiaming Wang
 * @version rev 1, Nov 3 2023
 */

public class Scene
{
    private HashMap<String, String> options;
    private String scene;
    private String sceneId;
    // might final work here?
    
    public String getScene() {
        return scene;
    }
    
    public String getSceneId() {
        return sceneId;
    }
    
    public Map<String, String> getOptions() {
        return Collections.unmodifiableMap(options);
    }
    
    public Scene(String sceneId, String sceneText, String... options) throws Exception
    {
        this.options = new HashMap<String, String>();
        
        for (String option : options) {
            String[] parts = option.split("\\|");
            // never will I use the vertical bar...
            String key = parts[0];
            String value = parts[1];
            this.options.put(key, value);
        }
        
        this.sceneId = sceneId;
        this.scene = sceneText;
    }
}
