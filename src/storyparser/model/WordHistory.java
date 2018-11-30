package storyparser.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class WordHistory implements Serializable {

    private Set<Word> words;

    public WordHistory(String filePath) {
        try {
            words = loadHistory(filePath);
        } catch (Exception e) {
            System.out.print("Could not load word history. Caught exception is:\n" + e);
            words = new HashSet<Word>();
        }
    }

    private Set<Word> loadHistory(String filePath) {
        //TODO
        return new HashSet<Word>();
    }

    public void save() {
        //TODO
    }
}
