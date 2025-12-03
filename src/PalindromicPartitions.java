import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class PalindromicPartitions {
    public static void main(String[] args) {
        String s = "aab";
        System.out.println(palindromicPartitions(s));
    }

    static List<List<String>> palindromicPartitions(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, result, new ArrayList<>(), 0);
        return result;
    }

    static void backtrack(String s, List<List<String>> result, List<String> curr, int index) {
        if (index == s.length()) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                curr.add(s.substring(index, i + 1));
                backtrack(s, result, curr, i + 1);
                curr.remove(curr.size() - 1);
            }
        }
    }

    static boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
