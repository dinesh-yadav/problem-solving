import java.util.*;
public class LetterCombinationOfPhoneNumber {
    public static void main(String[] args) {
        String digits = "232";
        System.out.println(letterCombinations(digits));
    }
    static public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        Map<Character, String> digitToChars = new HashMap<>();
        digitToChars.put('2', "abc");
        digitToChars.put('3', "def");
        digitToChars.put('4', "ghi");
        digitToChars.put('5', "jkl");
        digitToChars.put('6', "mno");
        digitToChars.put('7', "pqrs");
        digitToChars.put('8', "tuv");
        digitToChars.put('9', "wxyz");
        backtrack(digits, digitToChars, result, new StringBuilder(), 0);
        return result;
    }

    static void backtrack(String digits, Map<Character, String> digitToChars,
                   List<String> result, StringBuilder curr, int index) {
        if (index == digits.length()) {
            result.add(curr.toString());
            return;
        }

        String str = digitToChars.get(digits.charAt(index));
        for (int i = 0; i < str.length(); i++) {
            curr.append(str.charAt(i));
            backtrack(digits, digitToChars, result, curr, index + 1);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
