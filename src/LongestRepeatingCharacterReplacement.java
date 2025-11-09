import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Longest Repeating Character Replacement
 * Solved
 * You are given a string s consisting of only uppercase english
 * characters and an integer k. You can choose up to k characters of
 * the string and replace them with any other uppercase English character.
 *
 * After performing at most k replacements, return the length of the
 * longest substring which contains only one distinct character.
 *
 * Example 1:
 *
 * Input: s = "XYYX", k = 2
 *
 * Output: 4
 * Explanation: Either replace the 'X's with 'Y's, or replace the 'Y's with 'X's.
 *
 * Example 2:
 *
 * Input: s = "AAABABB", k = 1
 *
 * Output: 5
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * 0 <= k <= s.length
 *
 */
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        String s = "AAABABB";
        int k = 1;
        System.out.println(longestRepeatingCharReplacement(s, k));
        System.out.println(longestRepeatingCharReplacementOptimized(s, k));
    }

    /**
     * time complexity = O(m * n)
     * space complexity = O(m)
     * here n is length of the string
     * and m is total distinct character in the string which can only be 26 max.
     * @param s
     * @param k
     * @return
     */
    private static int longestRepeatingCharReplacement(String s, int k) {
        if (s == null || s.isEmpty())
            return 0;
        int len = s.length();
        if (len == 1)
            return 1;
        int result = 0;
        // put string chars into a set
        Set<Character> charSet = new HashSet<>();
        for (char c: s.toCharArray()) {
            charSet.add(c);
        }

        // we will check the count for each character
        // and count the result
        for (char c: charSet) {
            int count = 0;
            int left = 0;
            for (int right = 0; right < len; right++) {
                if (c == s.charAt(right)) {
                    count++;
                }

                while ((right - left + 1) - count > k) {
                    if (s.charAt(left) == c) {
                        count--;
                    }
                    left++;
                }

                result = Math.max(result, right - left + 1);
            }
        }

        return result;
    }

    /**
     * Here time complexity will be O(n)
     * and space complexity will be O(m)
     * here n is length of the string
     * and m is total distinct character in the string which can only be 26 max.
     * @param s
     * @param k
     * @return
     */
    private static int longestRepeatingCharReplacementOptimized(String s, int k) {
        if (s == null || s.isEmpty())
            return 0;
        int len = s.length();
        if (len == 1)
            return 1;
        int result = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int left = 0;
        int maxFreq = 0;
        for (int right = 0; right < len; right++) {
            char rc = s.charAt(right);
            charMap.put(rc, charMap.getOrDefault(rc, 0) + 1);
            maxFreq = Math.max(maxFreq, charMap.get(rc));

            // window size is more than the chars we can replace.
            // so we need to reduce the window size. with that we need to
            // reduce the count of left char also.
            while ((right - left + 1) - maxFreq > k) {
                char leftChar = s.charAt(left);
                charMap.put(leftChar, charMap.getOrDefault(leftChar, 0) - 1);
                left++;
            }
            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}
