import java.util.*;

/**
 * 2227. Encrypt and Decrypt Strings
 * Attempted
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given a character array keys containing unique characters and a string array values containing strings of length 2. You are also given another string array dictionary that contains all permitted original strings after decryption. You should implement a data structure that can encrypt or decrypt a 0-indexed string.
 *
 * A string is encrypted with the following process:
 *
 * For each character c in the string, we find the index i satisfying keys[i] == c in keys.
 * Replace c with values[i] in the string.
 * Note that in case a character of the string is not present in keys, the encryption process cannot be carried out, and an empty string "" is returned.
 *
 * A string is decrypted with the following process:
 *
 * For each substring s of length 2 occurring at an even index in the string, we find an i such that values[i] == s. If there are multiple valid i, we choose any one of them. This means a string could have multiple possible strings it can decrypt to.
 * Replace s with keys[i] in the string.
 * Implement the Encrypter class:
 *
 * Encrypter(char[] keys, String[] values, String[] dictionary) Initializes the Encrypter class with keys, values, and dictionary.
 * String encrypt(String word1) Encrypts word1 with the encryption process described above and returns the encrypted string.
 * int decrypt(String word2) Returns the number of possible strings word2 could decrypt to that also appear in dictionary.
 *
 *
 * Example 1:
 *
 * Input
 * ["Encrypter", "encrypt", "decrypt"]
 * [[['a', 'b', 'c', 'd'], ["ei", "zf", "ei", "am"], ["abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"]], ["abcd"], ["eizfeiam"]]
 * Output
 * [null, "eizfeiam", 2]
 *
 * Explanation
 * Encrypter encrypter = new Encrypter([['a', 'b', 'c', 'd'], ["ei", "zf", "ei", "am"], ["abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"]);
 * encrypter.encrypt("abcd"); // return "eizfeiam".
 *                            // 'a' maps to "ei", 'b' maps to "zf", 'c' maps to "ei", and 'd' maps to "am".
 * encrypter.decrypt("eizfeiam"); // return 2.
 *                               // "ei" can map to 'a' or 'c', "zf" maps to 'b', and "am" maps to 'd'.
 *                               // Thus, the possible strings after decryption are "abad", "cbad", "abcd", and "cbcd".
 *                               // 2 of those strings, "abad" and "abcd", appear in dictionary, so the answer is 2.
 *
 */
public class EncryptAndDecrypt {
}

class Encrypter {
    private Map<Character, String> charToString;
    private Map<String, List<Character>> stringToChar;
    private Set<String> dict;
    private Map<String, Integer> decryptMap;
    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        charToString = new HashMap<>();
        stringToChar = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            if (!stringToChar.containsKey(values[i])) {
                stringToChar.put(values[i], new ArrayList<>());
            }
        }

        for (int i = 0; i < keys.length; i++) {
            charToString.put(keys[i], values[i]);
            stringToChar.get(values[i]).add(keys[i]);
        }

        dict = new HashSet<>();
        for (int i = 0; i < dictionary.length; i++) {
            dict.add(dictionary[i]);
        }

        decryptMap = new HashMap<>();
    }

    public String encrypt(String word1) {
        StringBuilder encString = new StringBuilder();
        for (char ch: word1.toCharArray()) {
            encString.append(charToString.get(ch));
        }
        return encString.toString();
    }

    private void buildStrings(String word2, int index, String current,
                              List<String> result) {
        if (index >= word2.length()) {
            result.add(current.toString());
            return;
        }

        String key = ""+ word2.charAt(index) + word2.charAt(index+1);
        if (stringToChar.containsKey(key)) {
            for (char c: stringToChar.get(key)) {
                buildStrings(word2, index+2, current + c, result);
            }
        } else {
            return;
        }

    }

    public int decrypt(String word2) {
        if (decryptMap.containsKey(word2)) {
            return decryptMap.get(word2);
        }
        List<String> decStrings = new ArrayList<>();
        buildStrings(word2, 0, "", decStrings);
        System.out.println(decStrings);
        int count = 0;
        for (int i = 0; i < decStrings.size(); i++) {
            if(dict.contains(decStrings.get(i)))
                count++;
        }

        decryptMap.put(word2, count);
        return count;
    }
}

/**
 * Your Encrypter object will be instantiated and called as such:
 * Encrypter obj = new Encrypter(keys, values, dictionary);
 * String param_1 = obj.encrypt(word1);
 * int param_2 = obj.decrypt(word2);
 */


/**
 * correct and great approach
 */
class Encrypter1 {

    Map<String, Integer> encryptedDictCount;
    int[] keys;
    Set<String> dictionary;
    String[] val;

    public Encrypter1(char[] keys, String[] values, String[] dictionary) {
        this.keys = new int[26];
        encryptedDictCount = new HashMap<>();
        this.val = values.clone();
        this.dictionary = new HashSet<>(Arrays.asList(dictionary));

        for(int i=0; i<keys.length; i++) {
            this.keys[keys[i] - 'a'] = i;
        }

        for(String dict : dictionary) {
            String encrpted = encrypt(dict);
            encryptedDictCount.put(encrpted, encryptedDictCount.getOrDefault(encrpted, 0) + 1);
        }
    }

    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < word1.length(); i++) {
            int c = word1.charAt(i) - 'a';
            sb.append(val[keys[c]]);
        }
        return sb.toString();
    }

    public int decrypt(String word2) {
        return encryptedDictCount.getOrDefault(word2, 0);
    }
}
