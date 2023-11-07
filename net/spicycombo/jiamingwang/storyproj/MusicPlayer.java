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

    private LineListener listener;

    private AudioInputStream stream;

    /**
     * Initialize the music player
     * @param path The path of where the .wav file is located at
     */


    public MusicPlayer(String path, boolean jarFile, LineListener listener) throws Exception
    {
        this.listener = listener;

        ChangeTrack(path, jarFile, true);
    }
    
    public boolean start() {
        return start(false);
    }

    public void ChangeTrack(String path, boolean jarFile, boolean first) throws Exception {
        clip = AudioSystem.getClip();
        File file = new File(path);
        if (!jarFile) stream = AudioSystem.getAudioInputStream(file);
        else {
            // https://stackoverflow.com/questions/4056682/how-can-my-java-program-store-files-inside-of-its-jar-file
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
            stream = AudioSystem.getAudioInputStream(is);
        }

        clip.open(stream);
        clip.addLineListener(listener);
    }

    public boolean isPlaying() {
        if (clip != null) return clip.isRunning();
        else return false;
    }

    public boolean toggle() {
        if (clip != null) {
            if (isPlaying()) clip.stop();
            else clip.start();
            return true;
        }
        else return false;
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

    public static void CycleMusic(MusicPlayer mp, String path) {
        try {
            if (!Misc.runAsJarFile())
                mp.ChangeTrack(Misc.getRandomFile(new File(path)).getPath(), false, false);
            else {
                mp.start(); // lazy to implement something unique for jar files
            }
        }
        catch (Exception e) {
            if (Main.instanceTerminal != null) { Main.instanceTerminal.print("&rError! &w" + e.getMessage() + "^r"); }
            else { System.out.println("ERROR: " + e.getMessage()); }
        }
    }
}
