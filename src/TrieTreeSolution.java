import java.util.HashMap;
import java.util.Map;

/**
 * Implement Trie (Prefix Tree)
 * A prefix tree (also known as a trie) is a tree data structure used to efficiently store
 * and retrieve keys in a set of strings. Some applications of this data structure include
 * auto-complete and spell checker systems.
 *
 * Implement the PrefixTree class:
 *
 * PrefixTree() Initializes the prefix tree object.
 * void insert(String word) Inserts the string word into the prefix tree.
 * boolean search(String word) Returns true if the string word is in the prefix tree
 * (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is
 * a previously inserted string word that has the prefix prefix, and false otherwise.
 * Example 1:
 *
 * Input:
 * ["Trie", "insert", "dog", "search", "dog", "search", "do", "startsWith", "do", "insert", "do", "search", "do"]
 *
 * Output:
 * [null, null, true, false, true, null, true]
 *
 * Explanation:
 * PrefixTree prefixTree = new PrefixTree();
 * prefixTree.insert("dog");
 * prefixTree.search("dog");    // return true
 * prefixTree.search("do");     // return false
 * prefixTree.startsWith("do"); // return true
 * prefixTree.insert("do");
 * prefixTree.search("do");     // return true
 */

public class TrieTreeSolution {
    public static void main(String[] args) {
        String[] input = { "Trie", "insert", "dog", "search", "dog", "search", "do",
                "startsWith", "do", "insert", "do", "search", "do"};
        PrefixTree prefixTree = null;
        for (int i = 0; i < input.length; i++) {
            switch(input[i]) {
                case "Trie":
                    prefixTree = new PrefixTree();
                    break;
                case "insert":
                    i++;
                    prefixTree.insert(input[i]);
                    break;
                case "search":
                    i++;
                    System.out.println(prefixTree.search(input[i]));    // return true
                    break;
                case "startsWith":
                    i++;
                    System.out.println(prefixTree.startsWith(input[i]));    // return true
                    break;
            }
        }
    }
}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean endOfWord = false;
}

class PrefixTree {
    private TrieNode root;

    public PrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
        }
        cur.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return false;
            } else {
                cur = cur.children.get(c);
            }
        }
        return cur.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return false;
            } else {
                cur = cur.children.get(c);
            }
        }
        return true;
    }
}
