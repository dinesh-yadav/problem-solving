/**
 *  You are given a string s consisting only of 0 and 1.
 *
 * In one move, you can select any two characters of s and swap them.
 *
 * Return the minimum number of moves needed to make s a palindrome.
 *
 * Note that the input will be generated such that s can always be converted to a palindrome.
 */

class MinSwapsToPalindrome {
    public static void main(String[] args) {
        String str = "1010011";
        System.out.println(minSwapsToPalindrome(str));
    }

    /**
     * Finds the minimum number of single swaps (any two characters) to make
     * the string a palindrome.
     *
     * @param s The input string consisting only of '0's and '1's.
     * @return The minimum number of moves (single swaps) needed.
     */
    public static int minSwapsToPalindrome(String s) {
        // Convert the string to a char array for mutable swaps
        char[] chars = s.toCharArray();
        int n = chars.length;
        int left = 0;
        int right = n - 1;
        int moves = 0;

        while (left < right) {
            // Case 1: Characters already match
            if (chars[left] == chars[right]) {
                left++;
                right--;
                continue;
            }

            // Case 2: Characters mismatch. Find the best single-swap partner.

            // 1. Find the partner for chars[left] (value is chars[left])
            //    Scan from left+1 towards the center (right)
            int lMatch = -1;
            for (int i = left + 1; i <= right; i++) {
                if (chars[i] == chars[left]) {
                    lMatch = i;
                    break;
                }
            }

            // 2. Find the partner for chars[right] (value is chars[right])
            //    Scan from right-1 towards the center (left)
            int rMatch = -1;
            for (int i = right - 1; i >= left; i--) {
                if (chars[i] == chars[right]) {
                    rMatch = i;
                    break;
                }
            }

            // --- Core Greedy Decision ---

            // We only need to consider rMatch for swapping to the left position (left)
            // and lMatch for swapping to the right position (right).

            // The minimum moves is 1 for any direct swap. We are choosing which *pair* // to fix first based on the distance to the center.

            // The logic: If we swap rMatch into 'left', the remaining character is chars[left].
            // If we swap lMatch into 'right', the remaining character is chars[right].

            // Choose the character closest to the target position:
            // Option A: rMatch is a match for chars[right]. Swap rMatch with chars[left].
            //           Distance: rMatch - left
            // Option B: lMatch is a match for chars[left]. Swap lMatch with chars[right].
            //           Distance: right - lMatch

            // The 'moves' count is always 1 for the direct swap, but we prioritize
            // the swap that brings the required character *closest* to the current pointers.

            int costA = (rMatch == -1) ? Integer.MAX_VALUE : rMatch - left;
            int costB = (lMatch == -1) ? Integer.MAX_VALUE : right - lMatch;

            if (costA < costB) {
                // Option A: The character needed at 'left' (found at rMatch) is closer.
                // Swap rMatch with left to make chars[left] match chars[right].

                // Perform the required single swap: chars[rMatch] <-> chars[left]
                swap(chars, rMatch, left);
                moves++;

            } else {
                // Option B: The character needed at 'right' (found at lMatch) is closer.
                // Swap lMatch with right to make chars[right] match chars[left].

                // Perform the required single swap: chars[lMatch] <-> chars[right]
                swap(chars, lMatch, right);
                moves++;
            }

            // The mismatch is now resolved by the single swap.
            left++;
            right--;
        }

        return moves;
    }

    // Helper method to perform a single swap
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}