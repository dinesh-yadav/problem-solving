/**
 * Decrypt Message
 * An infamous gang of cyber criminals named “The Gray Cyber Mob”,
 * which is behind many hacking attacks and drug trafficking,
 * has recently become a target for the FBI. After intercepting some of their messages,
 * which looked like complete nonsense, the agency learned that they indeed encrypt their messages,
 * and studied their method of encryption.
 *
 * Their messages consist of lowercase latin letters only, and every word is encrypted separately as follows:
 *
 * Convert every letter to its ASCII value. Add 1 to the first letter, and then for every
 * letter from the second one to the last one, add the value of the previous letter.
 * Subtract 26 from every letter until it is in the range of lowercase letters a-z in ASCII.
 * Convert the values back to letters.
 *
 * For instance, to encrypt the word “crime”
 *
 * Decrypted message:	c	r	i	m	e
 * Step 1:	99	114	105	109	101
 * Step 2:	100	214	319	428	529
 * Step 3:	100	110	111	116	113
 * Encrypted message:	d	n	o	t	q
 * The FBI needs an efficient method to decrypt messages.
 * Write a function named decrypt(word) that receives a string that consists of small latin letters only,
 * and returns the decrypted word.
 *
 * Explain your solution and analyze its time and space complexities.
 *
 * Examples:
 *
 * input:  word = "dnotq"
 * output: "crime"
 *
 * input:  word = "flgxswdliefy"
 * output: "encyclopedia"
 * Since the function should be used on messages with many words,
 * make sure the function is as efficient as possible in both time and space.
 * Explain the correctness of your function, and analyze its asymptotic runtime and space complexity.
 *
 * Note: Most programing languages have built-in methods of converting
 * letters to ASCII values and vica versa. You may search the internet for the appropriate method.
 *
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] string word
 *
 * The ASCII value of every char is in the range of lowercase letters a-z.
 */
public class DecryptMessage {
    /**
     * * Decrypted message:	c	r	i	m	e
     *  * Step 1:	99	114	105	109	101
     *  * Step 2:	100	214	319	428	529
     *  * Step 3:	100	110	111	116	113
     *  * Encrypted message:	d	n	o	t	q
     * @param word
     * @return
     */
    static String decrypt(String word) {
        // dbotq
        if (word == null || word.isEmpty())
            return word;
        int numOfLetters = word.length();
        int prevVal = 1;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numOfLetters; i++) {
            int c = word.charAt(i) - prevVal;
            while (c < 'a' || c > 'z') {
                c = c + 26;
            }
            result.append((char)c);
            prevVal += c;

        }
        return result.toString();
    }

    public static void main(String[] args) {
        // debug your code below
        String word = "dnotq";
        String result = decrypt(word);
        System.out.println("Decrypted word: " + result);
    }
}
