import java.util.*;

/**
 * Group Anagrams
 * An anagram is a sequence of characters formed by rearranging the letters of another full sequence of characters.
 *
 * Given a list of strings, return a list of lists with each word grouped with its other anagrams.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input: ["eat", "bat", "ate", "tab", "tea", "eat"]
 * Output:
 * [
 *   ["eat", "ate", "tea", "eat"],
 *   ["bat", "tab"]
 * ]
 * Explanation:
 * 1.) "eat", "ate", "tea", "eat" are all anagrams w/ respect to each other
 * 2.) "bat", "tab" are all anagrams as well
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strings = {"eat", "bat", "ate", "tab", "tea", "eat"};
        List<List<String>> output = groupAnagrams(strings);
        for (List<String> list : output) {
            System.out.println(list);
        }
        System.out.println(output);
    }

    public static List<List<String>> groupAnagrams(String[] strings) {
        Map<String, List<String>> anagramMap = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            String key = createKey(strings[i]);
            if (!anagramMap.containsKey(key)) {
                List<String> tmp = new ArrayList<>();
                anagramMap.put(key, tmp);
            }
            anagramMap.get(key).add(strings[i]);
        }

        return new ArrayList<>(anagramMap.values());
    }

    public static String createKey(String str) {
        int[] mapping = new int[128];
        for (char c: str.toCharArray()) {
            mapping[c]++;
        }
        return Arrays.toString(mapping);
    }
}
