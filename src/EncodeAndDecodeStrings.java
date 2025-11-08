import java.util.ArrayList;
import java.util.List;

/**
 * Encode and Decode Strings
 * Solved
 * Design an algorithm to encode a list of strings to a single string.
 * The encoded string is then decoded back to the original list of strings.
 *
 * Please implement encode and decode
 *
 * Example 1:
 *
 * Input: ["neet","code","love","you"]
 *
 * Output:["neet","code","love","you"]
 * Example 2:
 *
 * Input: ["we","say",":","yes"]
 *
 * Output: ["we","say",":","yes"]
 * Constraints:
 * 0 <= strs.length < 100
 * 0 <= strs[i].length < 200
 * strs[i] contains only UTF-8 characters.
 */
public class EncodeAndDecodeStrings {
    public static void main(String[] args) {
        String[] input = {"neet","code","love","you"};
        String encodedString = encodeString(input);
        System.out.println(encodedString);
        System.out.println(decodedString(encodedString));
    }

    private static String encodeString(String[] strs) {
        StringBuilder result = new StringBuilder();
        for (String str: strs) {
            result.append(str.length()).append('#').append(str);
        }
        return result.toString();
    }

    private static List<String> decodedString(String str) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while(i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }

            int len = Integer.parseInt(str.substring(i, j));
            i = j + 1;
            j = i + len;
            result.add(str.substring(i, j));
            i = j;
        }

        return result;
    }
}
