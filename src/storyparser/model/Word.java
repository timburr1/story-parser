package storyparser.model;

import java.util.Set;

public class Word {

    private String name;
    private Set<String> forms;

    public Word(String name) {
        this.name = name;
    }

    public Word(String name, Set<String> forms) {
        this.name = name;
        this.forms = forms;
    }

    public boolean contains(String str) {
        return forms.contains(str);
    }

    public void add(String newForm) {
        this.forms.add(newForm);
    }
}
