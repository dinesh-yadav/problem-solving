import java.util.ArrayList;
import java.util.List;

/**
 * Minimum Removals for Valid String
 * Given a string s consisting of lowercase letters (a-z) as well as opening ( and closing parenthesis ),
 * remove a minimal number of opening or closing parentheses so that the resulting string is valid.
 *
 * Validity
 * We say that a string s is valid provided that at least one of the following three conditions hold:
 *
 * 1.) Base Case: s is the empty string, or s does not contain any opening or closing parentheses.
 *
 * 2.) Recursive Rule - Concatenation: s can be written in the form AB
 * (which denotes the string A concatenated with the string B), where A and B are both valid strings.
 *
 * 3.) Recursive Rule - Enclosed in Parens: s can be written in the form (A)
 * (which denotes an opening parenthesis ( followed by the string A followed by a closing parenthesis )),
 * where A is a valid string.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input: "a)b(c)d"
 * Output: "ab(c)d"
 * Explanation: We removed the first ")" parenthesis since it created a string that wouldn't be parsable.
 * Validity Tree (for the min-removal answer):
 *                 "ab(c)d" (rule 2)
 *             /             \
 *      "ab" (rule 1)   "(c)d" (rule 2)
 *                         /         \
 *                 "(c)" (rule 3)  "d" (rule 1)
 *                     /
 *             "c" (rule 1)
 */
public class MinRemovalToValidString {
    public static void main(String[] args) {
        //String input = "a)b(c)d";
        String input = "(a()b(c)d((";
        String output = minRemovalToValidString(input);
        System.out.println(output);
    }

    public static String minRemovalToValidString(String input) {
        if (input == null || input.isEmpty())
            return input;
        boolean[] remove = new boolean[input.length()];

        List<Integer> charIndexList = markCloseParentheses(remove, input);
        markOpenParentheses(charIndexList, remove, input);
        return buildOutput(remove, input);
    }

    public static List<Integer> markCloseParentheses(boolean[] remove, String input) {
        List<Integer> charIndexList = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ')') {
                if (charIndexList.isEmpty()) {
                    remove[i] = true;
                } else {
                    charIndexList.remove(charIndexList.size() - 1);
                }
            } else if (c == '(') {
                charIndexList.add(i);
            }
        }
        return charIndexList;
    }

    public static void markOpenParentheses(List<Integer> charIndexList, boolean[] remove, String input) {
        for(int index: charIndexList) {
            remove[index] = true;
        }
    }

    public static String buildOutput(boolean[] remove, String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < remove.length; i++) {
            if (!remove[i]) {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }
}
