import java.util.*;

/**
 * Word Break
 * Solved
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of dictionary words.
 *
 * You are allowed to reuse words in the dictionary an unlimited number of times. You may assume all dictionary words are unique.
 *
 * Example 1:
 *
 * Input: s = "neetcode", wordDict = ["neet","code"]
 *
 * Output: true
 * Explanation: Return true because "neetcode" can be split into "neet" and "code".
 *
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen","ape"]
 *
 * Output: true
 * Explanation: Return true because "applepenapple" can be split into "apple", "pen" and "apple". Notice that we can reuse words and also not use all the words.
 *
 * Example 3:
 *
 * Input: s = "catsincars", wordDict = ["cats","cat","sin","in","car"]
 *
 * Output: false
 * Constraints:
 *
 * 1 <= s.length <= 200
 * 1 <= wordDict.length <= 100
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 */
public class WordBreak {
    public static void main(String[] args) {
        String s = "applepenappl";
        List<String> wordDict = new ArrayList<>(Arrays.asList("apple","pen","ape"));
        System.out.println(wordBreak(s, wordDict));
    }

    static boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty())
            return true;
        if (wordDict == null || wordDict.isEmpty())
            return false;
        Set<String> wordSet = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return helper(s, wordSet, memo, 0);
    }

    static boolean helper(String s, Set<String> wordSet, Boolean[] memo, int index) {
        if (index == s.length()) {
            memo[index] = true;
            return memo[index];
        }

        if (memo[index] != null) {
            return memo[index];
        }

        int lenS = s.length();
        for (int i = index; i < lenS; i++) {
            String sub = s.substring(index, i + 1);
            if (wordSet.contains(sub)) {
                if (helper(s, wordSet, memo, i + 1)) {
                    memo[index] = true;
                    return memo[index];
                }
            }
        }
        memo[index] = false;
        return memo[index];
    }
}
