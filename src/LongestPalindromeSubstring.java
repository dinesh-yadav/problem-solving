/**
 * Longest Palindromic Substring
 * Solved
 * Given a string s, return the longest substring of s that is a palindrome.
 *
 * A palindrome is a string that reads the same forward and backward.
 *
 * If there are multiple palindromic substrings that have the same length, return any one of them.
 *
 * Example 1:
 *
 * Input: s = "ababd"
 *
 * Output: "bab"
 * Explanation: Both "aba" and "bab" are valid answers.
 *
 * Example 2:
 *
 * Input: s = "abbc"
 *
 * Output: "bb"
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s contains only digits and English letters.
 */
public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        String s = "abbcd"; //"ababd";
        System.out.println(longestPalindrome(s));
    }

    static String longestPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return s;
        }

        int lenS = s.length();
        int maxLen = 0;
        int resIndex = 0;

        for (int i = 0; i < lenS; i++) {
            // odd length
            int l = i;
            int r = i;
            while (l >= 0 && r < lenS && s.charAt(l) == s.charAt(r)) {
                int len = r - l + 1;
                if (len > maxLen) {
                    maxLen = len;
                    resIndex = l;
                }
                l--;
                r++;
            }

            // even length
            l = i;
            r = i + 1;
            while (l >= 0 && r < lenS && s.charAt(l) == s.charAt(r)) {
                int len = r - l + 1;
                if (len > maxLen) {
                    maxLen = len;
                    resIndex = l;
                }
                l--;
                r++;
            }
        }
        return s.substring(resIndex, resIndex + maxLen);
    }
}
