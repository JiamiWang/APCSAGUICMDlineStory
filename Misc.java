import java.io.*;
import java.util.regex.*;

public class Misc {
    public static final String softwareName = "Story Project";
    public static final String creator = "Jiaming Wang, Period 2, APCSA Course";

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

    // Non-immediate program exit
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

    // https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
    public static String reSub(String pattern, String replacement, String msg) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(msg);
        return m.replaceAll(replacement);
    }

    // "Sanitizes" a message. Simply removes the color and format tokens
    public static String sanitize(String input) {
        return reSub("^.", "", reSub("&.", "", input));
    }
}