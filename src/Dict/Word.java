package Dict;

import java.io.*;
import java.util.*;

public class Word {
    private String word_target;
    private ArrayList<String> word_explain;

    /**
     * Constructor
     */
    public Word(String word) {
        word_target = new String(word);
        word_explain = new ArrayList<String>();
    }

    public String getTarget() {
        return word_target;
    }

    public void addMean(String mean) {
        for (int i = 0; i < word_explain.size(); ++i) {
            if (word_explain.get(i).equals(mean)) {
                return;
            }
        }
        word_explain.add(mean);
    }

    public ArrayList<String> getMean() {
        return word_explain;
    }
}