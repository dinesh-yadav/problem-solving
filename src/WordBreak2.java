import java.util.*;

/**
 * 140. Word Break II
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * Example 2:
 *
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * Input is generated in a way that the length of the answer doesn't exceed 105.
 */
public class WordBreak2 {
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
        System.out.println(allSentences(s, wordDict));
        System.out.println(allSentencesMemo(s, wordDict));
        System.out.println(wordBreak(s, wordDict));
    }

    static List<String> allSentences(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return new ArrayList<>();
        }
        Set<String> dict = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        helper(s, dict, 0, result, "");
        return result;
    }

    private static void helper(String s, Set<String> dict, int index, List<String> result, String curr) {
        if (s.length() == index) {
            result.add(curr);
            return;
        }

        int n = s.length();
        for (int i = index; i < n; i++) {
            String sub = s.substring(index, i + 1);
            if (dict.contains(sub)) {
                String last = (i == n - 1)?"":" ";
                helper(s, dict, i + 1, result, curr + sub + last);
            }
        }
    }

    static List<String> allSentencesMemo(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return new ArrayList<>();
        }
        Set<String> dict = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        helperMemo(s, dict, 0, result, "", map);
        return result;
    }

    private static void helperMemo(String s, Set<String> dict, int index,
                                   List<String> result, String curr,
                                   Map<Integer, List<String>> map) {
        if (s.length() == index) {
            result.add(curr);
            map.put(index, result);
            return;
        }

        if (map.containsKey(index)) {
            return;
        }

        int n = s.length();
        for (int i = index; i < n; i++) {
            String sub = s.substring(index, i + 1);
            if (dict.contains(sub)) {
                String last = (i == n - 1)?"":" ";
                helperMemo(s, dict, i + 1, result, curr + sub + last, map);
            }
        }
    }



    static Map<Integer, List<String>> memo = new HashMap<>();
    static public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return helper(s, dict, 0);
    }

    private static List<String> helper(String s, Set<String> dict, int start) {
        // If we've already computed this substring, return it
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> res = new ArrayList<>();

        // Base case: reached the end of the string
        if (start == s.length()) {
            res.add("");
            return res;
        }

        for (int end = start; end < s.length(); end++) {
            String word = s.substring(start, end + 1);
            if (dict.contains(word)) {
                // Get all possible sentences from the remaining part
                List<String> sublist = helper(s, dict, end + 1);
                for (String sub : sublist) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }

        memo.put(start, res);
        return res;
    }
}
