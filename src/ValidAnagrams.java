import java.util.HashMap;
import java.util.Map;

/**
 * Valid Anagram
 * Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
 *
 * An anagram is a string that contains the exact same characters as another string,
 * but the order of the characters can be different.
 *
 * Example 1:
 *
 * Input: s = "racecar", t = "carrace"
 *
 * Output: true
 * Example 2:
 *
 * Input: s = "jar", t = "jam"
 *
 * Output: false
 * Constraints:
 *
 * s and t consist of lowercase English letters.
 */
public class ValidAnagrams {
    public static void main(String[] args) {
        String s = "racecar";
        String t = "carrace";
        System.out.println(isValidAnagram(s, t));
        System.out.println(validAnagramsAlternate(s, t));
        System.out.println(validAnagrams(s, t));
        s = "jar";
        t = "jam";
        System.out.println(isValidAnagram(s, t));
        System.out.println(validAnagramsAlternate(s, t));
    }

    private static boolean isValidAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> charMap = createHashMap(s);
        for (char c: t.toCharArray()) {
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c) - 1);
                if (charMap.get(c) == 0) {
                    charMap.remove(c);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> createHashMap(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        return charMap;
    }

    private static boolean validAnagramsAlternate(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] charMap = new int[26];
        for (char c : s.toCharArray()) {
            charMap[c - 'a']++;
        }

        for (char c: t.toCharArray()) {
            if (charMap[c - 'a'] == 0) {
                return false;
            } else {
                charMap[c - 'a']--;
            }
        }
        return true;
     }

    // slightly different
    private static boolean validAnagrams(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] charMap = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            charMap[c1 - 'a']++;
            charMap[c2 - 'a']--;
        }

        for(int i: charMap) {
            if (i != 0)
                return false;
        }
        return true;
    }
}
