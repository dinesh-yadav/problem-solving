/**
 * replace nth consonant to next consonant.
 * replace z to b and Z to B
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReplaceNthConsonantToNext {
    public static void main(String[] args) {
        System.out.println(replace("dineshZz", 2));
    }

    static String replace(String str, int n) {
        if (n > str.length())
            return str;
        StringBuilder sb = new StringBuilder();
        Set<Character> charSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int count = 0;
        for (char c: str.toCharArray()) {
            if (!charSet.contains(Character.toLowerCase(c))) {
                count++;
            }
            if (count < n) {
                sb.append(c);
            } else {
                if (c == 'z') {
                    c = 'b';
                } else if (c == 'Z') {
                    c = 'B';
                } else if (charSet.contains(Character.toLowerCase((char)(c + 1)))){
                    c = (char)(c + 2);
                } else {
                    c = (char)(c + 1);
                }
                sb.append(c);
                count = 0;
            }
        }
        return sb.toString();
    }
}
