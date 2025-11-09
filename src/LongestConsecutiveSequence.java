import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Longest Consecutive Sequence
 * Solved
 * Given an array of integers nums, return the length of the longest
 * consecutive sequence of elements that can be formed.
 *
 * A consecutive sequence is a sequence of elements in which each element
 * is exactly 1 greater than the previous element. The elements do not have to be consecutive in the original array.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 *
 * Input: nums = [2,20,4,10,3,4,5]
 *
 * Output: 4
 * Explanation: The longest consecutive sequence is [2, 3, 4, 5].
 *
 * Example 2:
 *
 * Input: nums = [0,3,2,5,4,6,1,1]
 *
 * Output: 7
 * Constraints:
 *
 * 0 <= nums.length <= 1000
 * -10^9 <= nums[i] <= 10^9
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = {2,20,4,10,3,4,5};
        System.out.println(lenOfLongestConsecutiveSequence(nums));
    }

    /**
     * Algo
     * - put all the elements in the set, so that we will have all the distinct
     * numbers.
     * - run a loop on all the numbers in the set.
     * - we will check only the numbers num for which num - 1 is not present.
     * because if num - 1 is present then num - 1 should have been the first number
     * of the sequence.
     * then if num - 1 is not present in the set, we will run a loop to check
     * if num + 1, num + 2 etc are present and count the length.
     * in the end, we will get the maxLength by comparing current length with
     * maxLength.
     * @param nums
     * @return
     */
    private static int lenOfLongestConsecutiveSequence(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        Set<Integer> numSet = new HashSet<>();
        for (int num: nums) {
            numSet.add(num);
        }
        int maxLen = 0;
        for (int num: numSet) {
            int len = 1;
            if (!numSet.contains(num - 1)) {
                while(numSet.contains(num + len)) {
                    len++;
                }
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}
