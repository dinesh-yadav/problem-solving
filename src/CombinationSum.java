import java.util.ArrayList;
import java.util.List;

/**
 * Combination Sum
 * You are given an array of distinct integers nums and a target integer target.
 * Your task is to return a list of all unique combinations of nums where the chosen numbers sum to target.
 *
 * The same number may be chosen from nums an unlimited number of times.
 * Two combinations are the same if the frequency of each of the chosen numbers is the same,
 * otherwise they are different.
 *
 * You may return the combinations in any order and the order of the numbers in each combination can be in any order.
 *
 * Example 1:
 *
 * Input:
 * nums = [2,5,6,9]
 * target = 9
 *
 * Output: [[2,2,5],[9]]
 * Explanation:
 * 2 + 2 + 5 = 9. We use 2 twice, and 5 once.
 * 9 = 9. We use 9 once.
 */

public class CombinationSum {
    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 9};
        int target = 9;
        System.out.println(combinationSum(nums, target));
    }

    private static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumHelper(nums, target, result, new ArrayList<>(), 0);
        return result;
    }

    private static void combinationSumHelper(int[] nums, int target, List<List<Integer>> result,
                                             List<Integer> current, int index) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0 || index >= nums.length)
            return;

        current.add(nums[index]);
        combinationSumHelper(nums, target - nums[index], result, current, index);
        current.remove(current.size() - 1);
        combinationSumHelper(nums, target, result, current, index+1);
    }
}
