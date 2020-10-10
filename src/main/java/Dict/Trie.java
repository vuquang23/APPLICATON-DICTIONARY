package Dict;

class Node {
    int index;
    Node[] child;

    Node() {
        index = -1;
        child = new Node[27];
        for (int i = 0; i < 27; ++i) {
            child[i] = null;
        }
    }
}

public class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    /* cur_size = -1: check if word in Dictionary now */
    public int findIndex(String word, int cur_size) {
        Node cur = root;
        for (int i = 0; i < word.length(); ++i) {
            int c;
            if (Character.compare(word.charAt(i), ' ') == 0) {
                c = 26;
            } else {
                c = word.charAt(i) - 'a';
            }

            if (cur.child[c] == null) {
                if (cur_size == -1) {
                    return -1;
                }
                cur.child[c] = new Node();
            }
            cur = cur.child[c];
        }
        if (cur.index == -1) {
            cur.index = cur_size;
        }
        return cur.index;
    }

    public void eraseWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); ++i) {
            int c;
            if (Character.compare(word.charAt(i), ' ') == 0) {
                c = 26;
            } else {
                c = word.charAt(i) - 'a';
            }
            cur = cur.child[c];
        }
        cur.index = -1;
    }
}