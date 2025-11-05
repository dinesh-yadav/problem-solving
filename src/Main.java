//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       String[]  words = {"word","world","row"};
       String order = "worldabcefghijkmnpqstuvxyz";
       int[] a = {1,2};
       String[] str = {"apple","app"}; // {"abcd", "abc", "bcd"};
      //  words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
      //System.out.println(""+ solution("hlabcdefgijkmnopqrstuvwxyz", new String[]{"hi","lets"}));
        System.out.println(solution(order, str));
    }
    //["hi","lets"],
    public static boolean solution(String order, String[] words) {
        int[] orderMap = new int[26];
        createMap(orderMap, order);

        for(int i = 0; i < words.length - 1; i++) {
            if (!inOrder(words[i], words[i+1], orderMap)) {
                return false;
            }
        }
        return true;
    }

    public static void createMap(int[] orderMap, String order) {
        for(int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            orderMap[c - 'a'] = i;
        }
    }

    public static boolean inOrder(String w1, String w2, int[] orderMap) {
        int len = Math.min(w1.length(), w2.length());
        for (int i = 0; i < len - 1; i++) {
            char ch1 = w1.charAt(i);
            char ch2 = w2.charAt(i);
            if (ch1 != ch2) {
                return orderMap[ch1 - 'a'] <= orderMap[ch2 - 'a'];
            }
        }
        System.out.println(w1 + " " + w2);
        System.out.println(w1.length() > w2.length());
        return w1.length() < w2.length();
    }
}

/*
Question
Let’s say a new nation is discovered called "MangoLand." In this nation, they only use lowercase English letters
for their language.
However, the order of the letters in their alphabet might be different from the standard English alphabet.

Given a array of words written in the this language, and the order of the alphabet,
return true if and only if the given words are sorted lexicographically  in this language.



Example 1:
Input: words = ["hi","lets"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

create orderMap
arr and put the index of letters as per give n string
if words == null
    return true
 "hi"
for i = 0 n-1
    id order of word is correct

        1 work[i] 2 word [i+1]
            if orderMap[chr1] > oraMap[chr2]
                return false


       if len word1 > len word2
        return
            keep a count and check if 2nd word is substring of first word

 return true

 working work

 */

/*
* Feedback about DSA:
*   - Ask questions about the problem
*       - about input
*       - give an example to clarify the question
*  - first list out all the edge cases.
*       - for this come up with few examples.
*  - if interviewer ask any question, think about it before answering.
*  - write sudo code clearly with modularity.
*  - follow the same code for implementation.
*  - for testing, use variables to pass in the solution.
*  - before submitting the answer, double-check the code.
*
* Feedback about experience
*   - explain the basics first so that interviewer will be able to understand a little.
*
*
*
* */