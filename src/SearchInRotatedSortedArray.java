/**
 * Search in Rotated Sorted Array
 * You are given an array of length n which was originally sorted in ascending order.
 * It has now been rotated between 1 and n times. For example, the array nums = [1,2,3,4,5,6] might become:
 *
 * [3,4,5,6,1,2] if it was rotated 4 times.
 * [1,2,3,4,5,6] if it was rotated 6 times.
 * Given the rotated sorted array nums and an integer target, return the index of target within nums,
 * or -1 if it is not present.
 *
 * You may assume all elements in the sorted rotated array nums are unique,
 *
 * A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,6,1,2], target = 1
 *
 * Output: 4
 * Example 2:
 *
 * Input: nums = [3,5,6,0,1,2], target = 4
 *
 * Output: -1
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -1000 <= target <= 1000
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {3,4,5,6,1,2};
        int target = 1;
        System.out.println(indexOfTarget(nums, target));
    }

    public static int indexOfTarget(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                if (target > nums[mid] || target < nums[start]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                if (target < nums[mid] || target > nums[end] ) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
}
