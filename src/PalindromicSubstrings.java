/**
 * Palindromic Substrings
 * Solved
 * Given a string s, return the number of substrings within s that are palindromes.
 *
 * A palindrome is a string that reads the same forward and backward.
 *
 * Example 1:
 *
 * Input: s = "abc"
 *
 * Output: 3
 * Explanation: "a", "b", "c".
 *
 * Example 2:
 *
 * Input: s = "aaa"
 *
 * Output: 6
 * Explanation: "a", "a", "a", "aa", "aa", "aaa".
 * Note that different substrings are counted as different palindromes even if the string contents are the same.
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 */
public class PalindromicSubstrings {
    public static void main(String[] args) {
        String s = "abbc";
        System.out.println(totalPalindromicSubstrings(s));
    }

    static int totalPalindromicSubstrings(String s) {
        if (s.isEmpty())
            return 0;
        int lenS = s.length();
        int count = 0;

        for (int i = 0; i < lenS; i++) {
            // odd length
            int l = i;
            int r = i;
            count += palindromicSubstrings(s, l, r);
            // even length
            r = i + 1;
            count += palindromicSubstrings(s, l, r);
        }
        return count;
    }

    static int palindromicSubstrings(String s, int l, int r) {
        int count = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            count++;
            l--;
            r++;
        }
        return count;
    }
}
