package storyparser;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StoryParser {

    final static String inputFilename = "input.txt";
    final static String outputFilename = "out.txt";

    static boolean isWhiteSpaceOrPunctuation(char ch) {
        if (ch < 'A') {
            return true;
        }
        if (ch > 'Z' && ch < 'a') {
            return true;
        }
        if (ch > 'z' && ch < '?') {
            return true;
        }

        return false;
    }

    static HashMap<String, Integer> ReadAndParseWords()
            throws FileNotFoundException, IOException {
        HashMap map = new HashMap<String, Integer>();

        InputStream in = new FileInputStream(new File(inputFilename));

        Reader reader = new BufferedReader(
                new InputStreamReader(in, Charset.forName("ISO-8859-1")));

        int r = 0;
        boolean doneReading = false;

        while (!doneReading) {
            r = reader.read();
            char ch = (char) r;

            StringBuilder b = new StringBuilder();

            while (!isWhiteSpaceOrPunctuation(ch)) {
                b.append(ch);

                r = reader.read();

                if (r == -1) {
                    doneReading = true;
                    break;
                } else {
                    ch = (char) r;
                }
            }

            String s = b.toString().toLowerCase();

            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, (Integer) map.get(s) + 1);
            }
        }

        in.close();

        return map;
    }

    static ArrayList<String> getKeys(HashMap map) {
        ArrayList<String> keys = new ArrayList<String>();

        for (Object s : map.keySet()) {
            keys.add((String) s);
        }

        return keys;
    }

    /**
     * @param args ignored
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(outputFilename)));

        HashMap map = ReadAndParseWords();

        ArrayList<String> keys = getKeys(map);
        Collections.sort(keys);

        for (String str : keys) {
            try {
                out.writeBytes(str + " " + map.get(str) + "\n");
            } catch (IOException ex) {
                System.out.print("\nCould not write " + str + " to file.");
            }
        }

        out.close();

        System.out.println("All finished.");
    }
}
