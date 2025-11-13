import java.util.Arrays;

/**
 * Given a string s that represents a special encoding, return the total ways that s can be decoded given the following mapping:
 *
 * 1 -> "a"
 * 2 -> "b"
 * 3 -> "c"
 * ...
 * 12 -> "l"
 * 13 -> "m"
 * 14 -> "n"
 * ...
 * 22 -> "v"
 * 23 -> "w"
 * 24 -> "x"
 * 25 -> "y"
 * 26 -> "z"
 * Input-Output
 *
 * Example 1
 *
 * Input: "123"
 * Output: 3
 * Explanation:
 * There are 3 possible valid & complete decodings:
 * 1.) ["1", "2", "3"] =>["a", "b", "c"]
 * 2.) ["12", "3"] => ["l", "c"]
 * 3.) ["1", "23"] => ["a", "w"]
 * Example 2
 *
 * Input: "33"
 * Output: 1
 * Explanation:
 * There is 1 possible valid & complete decoding:
 * 1.) ["3", "3"] =>["c", "c"]
 * Constraints
 *
 * 0 <= len(s)<= 100
 * The string will only contain digits 1 to 9
 *
 *                  1234
 *        234                   34
 *      34   4                 4
 *     4    -                  -
 *    -
 */

public class DecodeWays {

    public static void main(String[] args) {
        String s = "06";
        System.out.println(decodeWays(s));
    }
    public static int decodeWays(String s) {
        int[] cache = new int[s.length()];
        Arrays.fill(cache, -1);
        return helper(s, 0, cache);
    }

    public static int helper(String s, int index, int[] cache) {
        if (index >= s.length()) {
            return 1;
        }
        if(s.charAt(0) == '0')
            return 0;

        if (cache[index] != -1) {
            return cache[index];
        }
        int ways = 0;
        for (int i = 1; i <= 2; i++) {
            if (index + i <= s.length()) {
                String sub = s.substring(index, index + i);
                if (isValid(sub)) {
                    ways += helper(s, index + i, cache);
                }
            }
        }

        cache[index] = ways;
        return cache[index];
    }

    private static boolean isValid(String s) {
        return s.charAt(0) != '0' && Integer.parseInt(s) <= 26;
    }

}
