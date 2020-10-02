package Dict;

import java.io.*;
import java.util.*;

public class Dictionary {
    private ArrayList<Word> wordList;
    private Trie T;
    private HashMap<Integer, Boolean> erasedWord;
    private ArrayList<String> prefix;
    private ArrayList<String> meaningNULL;
    private ArrayList<String> allWord;

    /**
     * Constructor
     */
    public Dictionary() throws IOException {
        wordList = new ArrayList<Word>();
        T = new Trie();
        erasedWord = new HashMap<>();
        prefix = new ArrayList<String>();
        meaningNULL = new ArrayList<String>();
        allWord = new ArrayList<String>();

        FileReader fr = new FileReader(System.getProperty("user.dir") + "/src/Data/dictionaries.txt");
        BufferedReader br = new BufferedReader(fr);
        String[] myWord = new String[2];
        while (br.ready()) {
            myWord = br.readLine().split("\\t");
            StringBuilder target = new StringBuilder(myWord[0].trim());
            StringBuilder explain = new StringBuilder(myWord[1].trim());
            if (invalid(target)) {
                continue;
            }
            insertDict(target.toString(), explain.toString());
        }
        fr.close();
    }

    public static boolean invalid(StringBuilder s) {
        if (s.length() == 0) {
            System.out.println("Word should contain only character!!!");
            System.out.println("Plz input again!");
            return true;
        }
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                s.setCharAt(i, Character.toLowerCase(c));
            } else {
                if (Character.compare(c, ' ') != 0) {
                    System.out.println("Word should contain only character!!!");
                    System.out.println("Plz input again!");
                    return true;
                }
            }
        }
        return false;
    }

    public int lookUp(String a) {
        return T.findIndex(a, -1);
    }

    public ArrayList<String> getExplain(int id) {
        return wordList.get(id).getMean();
    }

    /************************PRE INIT************************************************************/
//    public void Init() throws IOException {
//
//    }

    /**
     * Add a new word
     */
    public void insertDict(String word, String mean) {
        int index = T.findIndex(word, wordList.size());
        if (index == wordList.size()) {
            Word newWord = new Word(word);
            wordList.add(newWord);
        }
        wordList.get(index).addMean(mean);
    }


    /*************************************SEARCH ALL PREFIX****************************************/
    public void DFS(Node u) {
        if (u.index != -1) {
            prefix.add(wordList.get(u.index).getTarget());
        }
        for (int i = 0; i < 27; ++i) {
            if (u.child[i] != null) {
                DFS(u.child[i]);
            }
        }
    }

    /* Show all Word have prefix is S */
    public ArrayList<String> searchPrefix(String s) {
        prefix.clear();
        StringBuilder builderS = new StringBuilder(s);
        if (invalid(builderS)) {
            return prefix;
        }
        s = builderS.toString();
        Node cur = T.root;
        int i = 0;
        for (; i < s.length(); ++i) {
            int c;
            if (s.charAt(i) == ' ') {
                c = 26;
            } else {
                c = s.charAt(i) - 'a';
            }

            if (cur.child[c] == null) {
                break;
            }
            cur = cur.child[c];
        }
        System.out.println("Maybe Found or Not:...");
        if (i == s.length()) {
            DFS(cur);
        }
        return prefix;
    }

    /********************GET MEANING*************************************/
    public ArrayList<String> getMeaning(String s) {
        StringBuilder sBuilder = new StringBuilder(s);
        if (invalid(sBuilder)) {
            return meaningNULL;
        }
        s = sBuilder.toString();
        int id = lookUp(s);
        if (id == -1) {
            return meaningNULL;
        }
        return getExplain(id);
    }


    /*******************ERASE A WORD***********************************************/
    public boolean eraseWord(String s) {
        StringBuilder sBuilder = new StringBuilder(s);
        if (invalid(sBuilder)) {
            return false;
        }
        s = sBuilder.toString();
        int index = lookUp(s);
        if (index == -1) {
            System.out.println("Word Not Existed In Dict - So Not Need To Be Removed!");
            return false;
        } else {
            T.eraseWord(s);
            erasedWord.put(index, true);
            return true;
        }

    }

    /*********************EDIT A WORD*********************************/
    public boolean editWord(String Target, String newTarget) {
        StringBuilder starget = new StringBuilder(Target);
        StringBuilder snewTarget = new StringBuilder(newTarget);
        if (invalid(starget) || invalid(snewTarget)) {
            return false;
        }
        Target = starget.toString();
        newTarget = snewTarget.toString();
        int index = lookUp(Target.toString());

        if (index == -1) {
            return false;
        } else {
            ArrayList<String> meanOfTarget = getExplain(index);
            for (int i = 0; i < meanOfTarget.size(); ++i) {
                insertDict(newTarget, meanOfTarget.get(i));
            }
            return eraseWord(Target);
        }
    }

    /*********************************ADD A WORD***********************************/
    public void insertAWord(String Target, String Meaning) {
        StringBuilder starget = new StringBuilder(Target);

        if (invalid(starget)) {
            return;
        }
        Target = starget.toString();
        insertDict(Target, Meaning);
    }


    /*************************************************************8*/
    public ArrayList<String> showWordList() {
        allWord.clear();
        for (int i = 0; i < wordList.size(); ++i) {
            String target = wordList.get(i).getTarget();
            if (erasedWord.containsKey(i) == true) {
                continue;
            }
            allWord.add(target);
        }
        return allWord;
    }
}