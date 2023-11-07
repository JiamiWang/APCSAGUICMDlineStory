package net.spicycombo.jiamingwang.storyproj;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;
import java.net.URL;

/**
 * Tools and other processes ad operations done by the executable
 * 
 * @author Jiaming Wang
 * @version rev 1, 3 Nov 2023
 */
public class Misc {
    public static final String softwareName = "Story Project";
    public static final String creator = "Jiaming Wang, Period 2, APCSA Course";

    public static final String[] creditLines = new String[] {
            "Music by Ozzed, used for free with license",
            "Ozzed Website: https://www.ozzed.net"
    };

    public static final String imagesPath = "./images/";
    public static final String musicPath = "./music/";

    public static final String welcomeASCII = "\r\n" +
       "   _____ __         " +
       "                    " +
       " \r\n" +
       "  / ___// /_____  __" +
       "_____  __           " +
       " \r\n" +
       "  \\__ \\/ __/ __ \\" +
       "/ ___/ / / /        " +
       "    \r\n" +
       " ___/ / /_/ /_/ / / " +
       " / /_/ /            " +
       " \r\n" +
       "/____/\\__/\\____/_/" +
       "   \\__, /          " +
       " __ \r\n" +
       "      / __ \\_______" +
       "_/____(_)__  _____/ " +
       "/_\r\n" +
       "     / /_/ / ___/ __" +
       " \\  / / _ \\/ ___/ " +
       "__/\r\n" +
       "    / ____/ /  / /_/" +
       " / / /  __/ /__/ /_ " +
       " \r\n" +
       "   /_/   /_/   \\___" +
       "_/_/ /\\___/\\___/\\" +
       "__/  \r\n" +
       "                   /" +
       "___/                " +
       " \r\n";

    /**
     * Non-immediate program exit
     * @param code The exit code upon executing the method
    */
    public static void PressAnyKey(int code) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Press enter key to exit...");
        try {
            input.readLine();
        } catch (Exception e) {
            // ignore the parameter e
            // catch is just so no exception is thrown
            // does not need to error IN ORDER for code to exit
        }

        System.exit(code); // exit by code
    }

    /**
     * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
     * Compiles and applies a Pattern on a String for modifying text
     * @return The modified input string with the applied Pattern
     */
    
    public static String reSub(String pattern, String replacement, String msg) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(msg);
        return m.replaceAll(replacement);
    }

    /**
     * "Sanitizes" a message. Simply removes the color and format tokens in the terminal
     * @return The "sanitized" input string
     */
    public static String sanitize(String input) {
        return reSub("\\^.", "", reSub("&.", "", input));
    }
    
    /**
     * Check whether or not executing from a jar file
     * @return true if executing within a jar file, false if not
     */
    public static boolean runAsJarFile() {
        URL resourceUrl = Class.class.getResource("MyClass.class");
        String protocol;
        try { protocol = resourceUrl.getProtocol(); } catch(Exception e) { return false; }
        return "jar".equals(protocol) || "rsrc".equals(protocol);
    }

    public static int randomNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1)) + start;
    }

    public static File getRandomFile(final File folder) {
        File[] folderContent = folder.listFiles();
        List<File> entries = new ArrayList<File>();
        for (final File fileEntry : folderContent) {
            if (!fileEntry.isDirectory()) continue;
            entries.add(fileEntry);
        }

        return entries.get(randomNum(0, entries.size() - 1));
    }
}
