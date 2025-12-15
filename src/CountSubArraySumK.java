import java.util.Map;
import java.util.HashMap;
/**
 * Count Subarrays That Sum to K
 * Given an array of unique integers and an integer value k,
 * return the total unique contiguous subarrays that sum to k in the array.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input: [1, 0, -1, 1], k = 0
 * Output: 4
 * Explanation: 4 subarrays sum up to 0
 *                 i j  (j inclusive)
 * [1, 0, -1, 1]  [1,1]
 * [1, 0, -1, 1]  [0,2]
 * [1, 0, -1, 1]  [1,3]
 * [1, 0, -1, 1]  [2,3]
 *  start = 0 end = 0
 *  sum if sum == k result++
 *  else if sum < k end++ and calculate the sum again
 *  else if sum > k start++ and calculate the sum
 *
 *  This is a hashMap question.
 *  prefixSum(i,j) = prefixSum(j - 1) - prefixSum(i)
 */
public class CountSubArraySumK {
    public static void main(String[] args) {
        int[] nums = {1, 0 , -1, 1};
        int k = 0;
        System.out.println(countSubArraySumK(nums, k));
    }

    public static int countSubArraySumK(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> keyToSum = new HashMap<>();
        keyToSum.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            //int key = k - sum;
            int key = sum - k;
            if (keyToSum.containsKey(key)) {
                count += keyToSum.get(key);
            }

            //keyToSum.put(key, keyToSum.getOrDefault(key, 0) + 1);
            keyToSum.put(sum, keyToSum.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
