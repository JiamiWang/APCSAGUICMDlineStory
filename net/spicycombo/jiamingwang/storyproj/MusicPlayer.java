package net.spicycombo.jiamingwang.storyproj;

import java.io.*;
import javax.sound.sampled.*;

/**
 * A music player (as the title suggests) to play music in theb background, refer to
 * https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
 * and
 * https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/package-summary.html
 * Ultimately, I had written this class before but I deleted it. So I wasted 20 minutes
 * @author Jiaming Wang
 * @version rev 1, 3 Nov 2023
 */
public class MusicPlayer
{
    private Clip clip;
    
    private AudioInputStream stream;

    /**
     * Initialize the music player
     * @param path The path of where the .wav file is located at
     */
    
    public MusicPlayer(String path) throws Exception {
        this(path, false);
    }
    
    public MusicPlayer(String path, boolean jarFile) throws Exception
    {
        clip = AudioSystem.getClip();
        File file = new File(path);
        if (!jarFile) stream = AudioSystem.getAudioInputStream(file);
        else {
            // https://stackoverflow.com/questions/4056682/how-can-my-java-program-store-files-inside-of-its-jar-file
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
            stream = AudioSystem.getAudioInputStream(is);
        }
        clip.open(stream);
    }
    
    public boolean start() {
        return start(true);
    }
    
    public boolean start(boolean loop) {
        if (clip != null && !clip.isRunning()) {
            if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
            else clip.loop(0);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            return true;
        } 
        else {
            return false;
        }
    }
    
    /**
     * Destroys this music player
     * @return true if successfully destroyed, false if it was already destroyed
     */
    public boolean destroy() {
        if (clip != null) {
            clip = null;
            return true;
        }
        else return false;
    }
}
