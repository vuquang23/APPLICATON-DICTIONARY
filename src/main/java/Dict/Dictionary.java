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
    static String path = System.getProperty("user.dir") + "/src/main/java/Data/";
    private ArrayList<String> bookmark;
    private ArrayList<String> realbookmark;
    private HashMap<String, Boolean> inBookmark;


    /**
     * Constructor
     */
    public Dictionary() {
        wordList = new ArrayList<Word>();
        T = new Trie();
        erasedWord = new HashMap<>();
        prefix = new ArrayList<String>();
        allWord = new ArrayList<String>();

        inBookmark = new HashMap<>();
        bookmark = new ArrayList<String>();
        realbookmark = new ArrayList<String>();

        /*Read dictionary*/
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + path + "dict_hh.db");
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

        /*read bookmark*/
        c = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + path + "bookmark.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM BOOKMARK;");
            while (result.next()) {
                String s1 = result.getString("word");
                StringBuilder ss1 = new StringBuilder(s1);
                if (invalid(ss1)) {
                    continue;
                }
                s1 = ss1.toString();
                bookmark.add(s1);
                inBookmark.put(s1, true);
            }
            result.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /* check wether a word is valid. */
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

    /* find index of String in WordList. */
    public int lookUp(String a) {
        return T.findIndex(a, -1);
    }

    /* get meaning of a String at id in Wordlist. */
    String getExplain(int id) {
        return wordList.get(id).getWord_explain();
    }

    /* insert a word to Dictionary. */
    public void insertDict(String word, String mean) {
        int index = T.findIndex(word, wordList.size());
        Word newWord = new Word(word, mean);
        wordList.add(newWord);
    }

    /* search a word with given prefix. */
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

    /* get meaning of a String. */
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


    /* erase a word. */
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

            /*erase from database*/
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" + path + "dict_hh.db");
                c.setAutoCommit(false);
                stmt = c.createStatement();
                String command = "DELETE FROM av where word LIKE '" + s + "';";
                stmt.executeUpdate(command);
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
            return 2;
        }
    }

    /* insert a word to database. */
    void insertToDatabase(String newTarget, String newExplain) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + path + "dict_hh.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String command = "INSERT INTO av (id,word,html,description,pronounce) " +
                    "VALUES (" + (wordList.size() + 10000000) + ", '" + newTarget + "', '" + newExplain + "', '#', '#' );";
            stmt.executeUpdate(command);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
        }
    }

    /* edit a word exited in dictionary. */
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

        /*insert to database*/
        insertToDatabase(newTarget, newExplain);
        return 4; /// success;
    }

    /* add a word. */
    public int insertAWord(String Target, String Meaning) {
        StringBuilder starget = new StringBuilder(Target);

        if (invalid(starget)) {
            return 0; // invalid format word
        }
        Target = starget.toString();
        if (lookUp(Target) != -1) {
            return 1; // word existed in dict
        }

        Target = starget.toString();
        Meaning = "<h1>" + Target + "</h1>"
                + "<ul>" + Meaning + "</ul>";
        insertDict(Target, Meaning);
        insertToDatabase(Target, Meaning);
        return 2; // success
    }

    /*show all word.*/
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

    /*show favourite word.*/
    public ArrayList<String> showBookMark() {
        realbookmark.clear();
        for (int i = 0; i < bookmark.size(); ++i) {
            String target = bookmark.get(i);
            if (inBookmark.containsKey(target)) {
                realbookmark.add(target);
            }
        }
        return realbookmark;
    }

    /*insert a word to bookmark.*/
    public boolean insertToBookMark(String s) {
        if (inBookmark.containsKey(s)) {
            return false;
        }
        inBookmark.put(s, true);
        bookmark.add(s);

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + path + "bookmark.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String command = "INSERT INTO BOOKMARK (word) " +
                    "VALUES (" + "'" + s + "'" + ");";
            stmt.executeUpdate(command);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
        }

        return true;
    }

    /*remove from bookmark.*/
    public void removeFromBookMark(String s) {
        inBookmark.remove(s);
        bookmark.remove(s);

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + path + "bookmark.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String command = "DELETE FROM BOOKMARK where word LIKE '" + s + "';";
            stmt.executeUpdate(command);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

}