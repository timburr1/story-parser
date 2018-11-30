package storyparser.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Model {

    private String story;
    private WordHistory history;
    private Map<String, Integer> wordCounts;

    public Model() {
        wordCounts = new HashMap<String, Integer>();
        wordCounts.put("Estar", 3);
        wordCounts.put("Prueba", 1);
        wordCounts.put("Ser", 2);
    }
    
    public Set<String> getWords() {
        return this.wordCounts.keySet();
    }
    
    public Integer getOccurances(String word) {
        Integer ret = wordCounts.get(word);
        
        if(ret == null)
            ret = 0;
        
        return ret;
    }
}
