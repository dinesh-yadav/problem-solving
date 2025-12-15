import java.util.HashSet;
import java.util.Set;

/**
 * 2367. Number of Arithmetic Triplets
 * You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff. A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:
 *
 * i < j < k,
 * nums[j] - nums[i] == diff, and
 * nums[k] - nums[j] == diff.
 * Return the number of unique arithmetic triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,4,6,7,10], diff = 3
 * Output: 2
 * Explanation:
 * (1, 2, 4) is an arithmetic triplet because both 7 - 4 == 3 and 4 - 1 == 3.
 * (2, 4, 5) is an arithmetic triplet because both 10 - 7 == 3 and 7 - 4 == 3.
 */
public class NumberOfArithmeticTriplet {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,8,9};//{0, 1, 4, 6, 7, 10};
        int diff = 2; //3;
        System.out.println(arithmeticTriplets(nums, diff));
        System.out.println(arithmeticTripletsBinarySearch(nums, diff));
    }

    // time O(N) space O(N)
    static public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        for (int i:nums) {
            set.add(i);
        }
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (set.contains(nums[i] + diff) && set.contains(nums[i] + 2*diff)) {
                count++;
            }
        }
        return count;
    }

    // time O(nlogn) and space O(1)
    static public int arithmeticTripletsBinarySearch(int[] nums, int diff) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (binarySearch(nums, i + 1, nums[i] + diff) &&
                    binarySearch(nums, i + 2, nums[i] + 2 * diff)) {
                count++;
            }
        }
        return count;
    }

    static boolean binarySearch(int[] nums, int index, int num) {
        int l = index;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l)/2;
            if (nums[mid] == num) {
                return true;
            } else if (nums[mid] < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
