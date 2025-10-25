/**
 * Given a string s, return the length of the longest substring of s without repeating characters.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input: "ABCABADEC"
 * Output: 5
 * Explanation: Though there are substrings such as "AB" and "ABC" that have all unique characters,
 * "BADEC" is the longest substring w/ no character repeats.
 */

public class LongestSubstringWithoutRepeating {
    public static void main(String[] args) {
        String str = "ABCABADEC";
        System.out.println(longestSubstring(str));
    }

    public static int longestSubstring(String str) {
        int maximum = 0;
        int start = 0;
        int end = 0;
        int[] mapping = new int[108];
        while (end < str.length()) {
            int index = str.charAt(end);
            start = Math.max(start, mapping[index]);
            mapping[index] = end + 1;
            maximum = Math.max(maximum, end - start + 1);
            end++;
        }
        return maximum;
    }
}
