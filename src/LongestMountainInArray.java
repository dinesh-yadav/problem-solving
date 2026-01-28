import java.util.Arrays;

/**
 * 845. Longest Mountain in Array
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * Example 2:
 *
 * Input: arr = [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 104
 *
 *
 * Follow up:
 *
 * Can you solve it using only one pass?
 * Can you solve it in O(1) space?
 */
public class LongestMountainInArray {
    public static void main(String[] args) {
        int[] nums = {2,1,4,7,3,2,5};
        System.out.println(longestMountainDp(nums));
        System.out.println(longestMountainGreedy(nums));
    }

    // DP time O(n) space O(n)
    static int longestMountainDp(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        int n = nums.length;
        int[] up = new int[n];
        Arrays.fill(up, 1);
        int[] down = new int[n];
        Arrays.fill(down, 1);
        int ans = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = up[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                down[i] = down[i + 1] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (up[i] > 1 && down[i] > 1) {
                ans = Math.max(ans, up[i] + down[i] - 1);
            }
        }
        return ans;
    }

    //time O(n) space O(1)
    static int longestMountainGreedy(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int up = 0;
        int down = 0;
        int ans = 0;
        int i = 0;
        int n = nums.length;
        while(i < n - 1) {
            if(nums[i] >= nums[i + 1]) {
                i++;
            } else {
                while(i < n - 1 && nums[i] < nums[i + 1]) {
                    i++; up++;
                }

                while(i < n - 1 && nums[i] > nums[i + 1]) {
                    i++; down++;
                }
                if (up > 0 && down > 0) {
                    ans = Math.max(ans, up + down + 1);
                }
                up = 0;
                down = 0;
            }
        }
        return ans;
    }
}
