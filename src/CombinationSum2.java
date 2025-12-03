import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * Medium
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        int[] nums = {10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(combinationSum2(nums, target));
    }

    static List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, result, target, new ArrayList<>(), 0);
        return result;

    }

//    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
//        if(remain < 0)
//            return;
//        if(remain == 0) {
//            list.add(new ArrayList<>(tempList));
//            return;
//        }
//
//        for(int i = start; i < nums.length; i++){
//            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
//            tempList.add(nums[i]);
//            backtrack(list, tempList, nums, remain - nums[i], i + 1);
//            tempList.remove(tempList.size() - 1);
//        }
//    }

    static void backtrack(int[] nums, List<List<Integer>> result, int target, List<Integer> curr, int index) {
        if (target < 0)
            return;
        if (target == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1])
                continue;
            curr.add(nums[i]);
            backtrack(nums, result, target - nums[i], curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}
