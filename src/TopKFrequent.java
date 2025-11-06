import java.util.*;

/**
 * Top K Frequent Elements
 * Given an integer array nums and an integer k, return the k most frequent elements within the array.
 *
 * The test cases are generated such that the answer is always unique.
 *
 * You may return the output in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,3,3,3], k = 2
 *
 * Output: [2,3]
 * Example 2:
 *
 * Input: nums = [7,7], k = 1
 *
 * Output: [7]
 * Constraints:
 *
 * 1 <= nums.length <= 10^4.
 * -1000 <= nums[i] <= 1000
 * 1 <= k <= number of distinct elements in nums.
 *
 * Bucket sort
 */
public class TopKFrequent {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3,3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length)
            return new int[0];

        Map<Integer, Integer> frqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            frqMap.put(nums[i], frqMap.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];

        for (int key : frqMap.keySet()) {
            int frequency = frqMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        int[] result = new int[k];
        int count = 0;
            for (int i = nums.length; i >= 0 && count < k; i--) {
                if (bucket[i] != null) {
                    for (int n : bucket[i]) {
                        result[count++] = n;
                        if (count == k)
                            return result;
                    }
                }

        }
        return result;
    }
}
