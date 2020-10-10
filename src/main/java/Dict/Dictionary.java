package Dict;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
public class Dictionary {
    private ArrayList<Word> wordList;
    private Trie T;
    private HashMap<Integer, Boolean> erasedWord;
    private ArrayList<String> prefix;
    private ArrayList<String> allWord;

    /**
     * Constructor
     */
    public Dictionary() {
        wordList = new ArrayList<Word>();
        T = new Trie();
        erasedWord = new HashMap<>();
        prefix = new ArrayList<String>();
        allWord = new ArrayList<String>();

        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = System.getProperty("user.dir") + "/src/main/java/Data/dict_hh.db";
            c = DriverManager.getConnection("jdbc:sqlite:" + path);
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM av;");
            while (result.next()) {
                String s1 = result.getString("word");
                String s2 = result.getString("html");
                StringBuilder ss1 = new StringBuilder(s1);
                if (invalid(ss1)) {
                    continue;
                }
                s1 = ss1.toString();
                insertDict(s1, s2);
            }
            result.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
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
                if (Math.abs(s.charAt(i) - 'a') > 25) {
                    return true;
                }
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

    String getExplain(int id) {
        return wordList.get(id).getWord_explain();
    }

    /************************PRE INIT************************************************************/
    /**
     * Add a new word
     */
    public void insertDict(String word, String mean) {
        int index = T.findIndex(word, wordList.size());
        Word newWord = new Word(word, mean);
        wordList.add(newWord);
    }


    /*************************************SEARCH ALL PREFIX****************************************/
    public void DFS(Node u) {
        if (u.index != -1) {
            prefix.add(wordList.get(u.index).getWord_target());
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
    public String getMeaning(String s) {
        StringBuilder sBuilder = new StringBuilder(s);
        if (invalid(sBuilder)) {
            return "-1";
        }
        s = sBuilder.toString();
        int id = lookUp(s);
        if (id == -1) {
            return "-1";
        }
        return getExplain(id);
    }


    /*******************ERASE A WORD***********************************************/
    public int eraseWord(String s) {
        StringBuilder sBuilder = new StringBuilder(s);
        if (invalid(sBuilder)) {
            return 0; // Wrong format of word
        }
        s = sBuilder.toString();
        int index = lookUp(s);
        if (index == -1) {
            System.out.println("Word Not Existed In Dict - So Not Need To Be Removed!");
            return 1;
        } else {
            T.eraseWord(s);
            erasedWord.put(index, true);
            return 2;
        }

    }

    /*********************EDIT A WORD*********************************/
    public int editWord(String Target, String newTarget, String newExplain) {
        StringBuilder starget = new StringBuilder(Target);
        StringBuilder snewTarget = new StringBuilder(newTarget);

        if (invalid(starget) || invalid(snewTarget)) {
            return 1; /// invalid format of word
        }
        Target = starget.toString();
        newTarget = snewTarget.toString();
        int index = lookUp(Target);
        if (index == -1) {
            return 2; /// word not in dict
        }

        if (lookUp(newTarget) != -1) {
            return 3; /// new word existed in dict
        }

        eraseWord(Target);
        newExplain = "<h1>" + newTarget + "</h1>"
                + "<ul>" + newExplain + "</ul>";
        insertDict(newTarget, newExplain);
        return 4; /// success;
    }

    /*********************************ADD A WORD***********************************/
    public int insertAWord(String Target, String Meaning) {
        StringBuilder starget = new StringBuilder(Target);

        if (invalid(starget)) {
            return 0; // invalid format word
        }
        if (lookUp(Target) != -1) {
            return 1; // word existed in dict
        }

        Target = starget.toString();
        Meaning = "<h1>" + Target + "</h1>"
                + "<ul>" + Meaning + "</ul>";
        insertDict(Target, Meaning);
        return 2; // success
    }


    /*************************************************************8*/
    public ArrayList<String> showWordList() {
        allWord.clear();
        for (int i = 0; i < wordList.size(); ++i) {
            String target = wordList.get(i).getWord_target();
            if (erasedWord.containsKey(i)) {
                continue;
            }
            allWord.add(target);
        }
        return allWord;
    }
}