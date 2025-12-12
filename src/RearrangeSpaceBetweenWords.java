import java.util.ArrayList;
import java.util.List;

/**
 * 1592. Rearrange Spaces Between Words
 * Solved
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given a string text of words that are placed among some number of spaces. Each word consists of one or more lowercase English letters and are separated by at least one space. It's guaranteed that text contains at least one word.
 *
 * Rearrange the spaces so that there is an equal number of spaces between every pair of adjacent words and that number is maximized. If you cannot redistribute all the spaces equally, place the extra spaces at the end, meaning the returned string should be the same length as text.
 *
 * Return the string after rearranging the spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "  this   is  a sentence "
 * Output: "this   is   a   sentence"
 * Explanation: There are a total of 9 spaces and 4 words. We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.
 * Example 2:
 *
 * Input: text = " practice   makes   perfect"
 * Output: "practice   makes   perfect "
 * Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space. We place this extra space at the end of the string.
 */
public class RearrangeSpaceBetweenWords {
    public static void main(String[] args) {
        String text = "  this   is  a sentence ";
        System.out.println(rearrangeSpacesBetweenWords(text));
        System.out.println(rearrangeSpacesBetweenWords(" practice   makes   perfect"));
    }

    static String rearrangeSpacesBetweenWords(String text) {
        List<String> words = new ArrayList<>();
        StringBuilder curr = new StringBuilder();
        int n = text.length();
        int spaces = 0;
        for (int i = 0; i < n; i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                spaces++;
                if (!curr.isEmpty()) {
                    words.add(curr.toString());
                    curr = new StringBuilder();
                }
            } else {
                curr.append(ch);
            }
        }

        if (!curr.isEmpty()) {
            words.add(curr.toString());
        }

        int wordCount = words.size();
        int spaceEach = wordCount == 1? 0:spaces/(wordCount - 1);
        int extra = wordCount == 1? spaces:spaces%(wordCount - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            sb.append(words.get(i));
            if (i != wordCount - 1) {
                sb.append(" ".repeat(spaceEach));
            } else {
                sb.append(" ".repeat(extra));
            }
        }
        return sb.toString();
    }
}
