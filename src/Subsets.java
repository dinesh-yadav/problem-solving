import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Subsets 1 and 2.
 * find out the subsets of a given array with and without duplicate.
 */
public class Subsets {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] numsWithDuplicate = {2, 1, 2};
        System.out.println(subsetsWithoutDuplicate(nums));
        System.out.println(subsetsWithDuplicate(numsWithDuplicate));
    }

    static List<List<Integer>> subsetsWithoutDuplicate(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack1(nums, result, new ArrayList<>(), 0);
        return result;
    }

    static void backtrack1(int[] nums, List<List<Integer>> result, List<Integer> curr, int index) {
        result.add(new ArrayList<>(curr));
        for (int i = index; i < nums.length; i++) {
            curr.add(nums[i]);
            backtrack1(nums, result, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    static List<List<Integer>> subsetsWithDuplicate(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(nums, result, new ArrayList<>(), 0);
        return result;
    }

    static void backtrack2(int[] nums, List<List<Integer>> result, List<Integer> curr, int index) {
        result.add(new ArrayList<>(curr));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i])
                continue;
            curr.add(nums[i]);
            backtrack2(nums, result, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }




}
