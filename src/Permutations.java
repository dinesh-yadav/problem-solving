import java.util.*;

/**
 * Permutations 1 and 2.
 * in 2 we have duplicates.
 */
public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] numsWithDuplicate = {2, 1, 2};
        System.out.println(permutationsWithoutDuplicate(nums));
        System.out.println(permutationsWithDuplicate(numsWithDuplicate));
    }

    static List<List<Integer>> permutationsWithoutDuplicate(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack1(nums, result, new ArrayList<>());
        return result;
    }

    static void backtrack1(int[] nums, List<List<Integer>> result, List<Integer> curr) {
        if (curr.size() == nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (curr.contains(nums[i]))
                continue;
            curr.add(nums[i]);
            backtrack1(nums, result, curr);
            curr.remove(curr.size() - 1);
        }
    }

    static List<List<Integer>> permutationsWithDuplicate(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack2(nums, result, new ArrayList<>(), used);
        return result;
    }

    static void backtrack2(int[] nums, List<List<Integer>> result, List<Integer> curr, boolean[] used) {
        if (curr.size() == nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            if (i > 0 && used[i - 1] && nums[i - 1] == nums[i])
                continue;
            used[i] = true;
            curr.add(nums[i]);
            backtrack2(nums, result, curr, used);
            used[i] = false;
            curr.remove(curr.size() - 1);
        }
    }
}
