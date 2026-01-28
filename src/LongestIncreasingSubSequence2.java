import java.util.Arrays;

/**
 * 2407. Longest Increasing Subsequence II
 * You are given an integer array nums and an integer k.
 *
 * Find the longest subsequence of nums that meets the following requirements:
 *
 * The subsequence is strictly increasing and
 * The difference between adjacent elements in the subsequence is at most k.
 * Return the length of the longest subsequence that meets the requirements.
 *
 * A subsequence is an array that can be derived from another array
 * by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
 * Output: 5
 * Explanation:
 * The longest subsequence that meets the requirements is [1,3,4,5,8].
 * The subsequence has a length of 5, so we return 5.
 * Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3.
 * Example 2:
 *
 * Input: nums = [7,4,5,1,8,12,4,7], k = 5
 * Output: 4
 * Explanation:
 * The longest subsequence that meets the requirements is [4,5,8,12].
 * The subsequence has a length of 4, so we return 4.
 * Example 3:
 *
 * Input: nums = [1,5], k = 1
 * Output: 1
 * Explanation:
 * The longest subsequence that meets the requirements is [1].
 * The subsequence has a length of 1, so we return 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 105
 */
public class LongestIncreasingSubSequence2 {
    public static void main(String[] args) {
        int[] nums = {7,4,5,1,8,12,4,7};
        int k = 5;
        System.out.println(longestSubSequence(nums, k));
        System.out.println(longestSubSequenceOpti(nums, k));
    }

    // time O(n^2)
    static int longestSubSequence(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxSize = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && (nums[i] - nums[j]) <= k) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxSize = Math.max(maxSize, dp[i]);
        }
        return maxSize;
    }

    // time O(n^k)
    static int longestSubSequenceOpti(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int[] dp = new int[100001];
        int maxSize = 1;

        for (int i = 0; i < n; i++) {
            for (int j = Math.max(0, nums[i] - k); j < nums[i]; j++) {
                dp[nums[i]] = Math.max(dp[nums[i]], dp[j] + 1);
            }
            maxSize = Math.max(maxSize, dp[nums[i]]);
        }
        return maxSize;
    }

    class Node {
        Node leftChild;
        Node rightChild;
        int start;
        int end;
        int value;

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }

    class Solution {
        Node buildSegmentTree(int start, int end) {
            if (start == end)
                return new Node(start, end, 0);
            Node node = new Node(start, end, 0);
            int mid = (start + end) / 2;
            node.leftChild = buildSegmentTree(start, mid);
            node.rightChild = buildSegmentTree(mid + 1, end);
            return node;
        }

        int queryRangeMax(Node node, int l, int r) {
            if (node == null || l > node.end || r < node.start)
                return 0; // Return 0 for out-of-bound queries
            if (l <= node.start && r >= node.end)
                return node.value; // Total overlap
            // Partial overlap
            return Math.max(queryRangeMax(node.leftChild, l, r), queryRangeMax(node.rightChild, l, r));
        }

        void updateSegmentTree(Node node, int index, int value) {
            if (node == null || index < node.start || index > node.end)
                return; // Out of bounds
            node.value = Math.max(value, node.value); // Update the current node
            if (node.start != node.end) { // If it's not a leaf node
                updateSegmentTree(node.leftChild, index, value);
                updateSegmentTree(node.rightChild, index, value);
            }
        }

        public int lengthOfLIS(int[] nums, int k) {
            Node root = buildSegmentTree(0, 100001);
            int ans = 1;
            for (int num : nums) {
                int maxValInRange = queryRangeMax(root, Math.max(0, num - k), num - 1) + 1;
                ans = Math.max(ans, maxValInRange);
                updateSegmentTree(root, num, maxValInRange);
            }
            return ans;
        }
    }
}

/**
 * class Solution {
 *     public int lengthOfLIS(int[] nums, int k) {
 *         int max = 0;
 *         for(int num : nums){
 *             max = Math.max(max, num);
 *         }
 *         int N = 1;
 *         while (N < max){
 *             N <<= 1;
 *         }
 *         // System.out.println("N=" + N);
 *
 *         // segment tree
 *         int[] tree = new int[2*N];
 *
 *         int result = 0;
 *         for(int num : nums) {
 *             int left = Math.max(1, num - k) + (N-1);
 *             int right = num - 1 + (N-1);
 *
 *             int best = 0;
 *             while(left <= right){
 *                 if((left & 1) == 1) {
 *                     best = Math.max(best, tree[left++]);
 *                 }
 *                 if((right & 1) == 0) {
 *                     best = Math.max(best, tree[right--]);
 *                 }
 *                 left >>= 1;
 *                 right >>= 1;
 *             }
 *
 *             int len = best + 1;
 *             result = Math.max(result, len);
 *
 *             int index = num + (N-1);
 *             if(tree[index] < len) {
 *                 tree[index] = len;
 *                 index >>= 1;
 *                 while(index > 0) {
 *                     tree[index] = Math.max(tree[index], len);
 *                     index >>= 1;
 *                 }
 *             }
 *         }
 *
 *         return result;
 *
 *     }
 * }
 *
 *
 * class Solution2 {
 *
 *     public int lengthOfLIS(int[] nums, int k) {
 *         int max = 0;
 *         for (int num : nums) max = Math.max(max, num);
 *
 *         int n = 1;
 *         while (n <= max) n <<= 1;
 *
 *         int[] tree = new int[n * 2];
 *         int res = 0;
 *
 *         for (int num : nums) {
 *             int left = Math.max(1, num - k) + n;
 *             int right = num - 1 + n;
 *             int cur = 0;
 *
 *             while (left <= right) {
 *                 if ((left & 1) == 1) cur = Math.max(cur, tree[left++]);
 *                 if ((right & 1) == 0) cur = Math.max(cur, tree[right--]);
 *                 left >>= 1;
 *                 right >>= 1;
 *             }
 *
 *             int len = cur + 1;
 *             res = Math.max(res, len);
 *
 *             int idx = num + n;
 *             if (tree[idx] < len) {
 *                 tree[idx] = len;
 *                 idx >>= 1;
 *                 while (idx > 0) {
 *                     tree[idx] = Math.max(tree[idx << 1], tree[(idx << 1) | 1]);
 *                     idx >>= 1;
 *                 }
 *             }
 *         }
 *         return res;
 *     }
 * }
 *
 *
 * class Solution3 {
 *
 *     int N = 100001;
 *     int[] seg = new int[2*N];
 *
 *     void update(int pos, int val){  // update max
 *         pos += N;
 *         seg[pos] = val;
 *
 *         while (pos > 1) {
 *             pos >>= 1;
 *             seg[pos] = Math.max(seg[2*pos], seg[2*pos+1]);
 *         }
 *     }
 *
 *     int query(int lo, int hi){ // query max [lo, hi)
 *         lo += N;
 *         hi += N;
 *         int res = 0;
 *
 *         while (lo < hi) {
 *             if ((lo & 1)==1) {
 *                 res = Math.max(res, seg[lo++]);
 *             }
 *             if ((hi & 1)==1) {
 *                 res = Math.max(res, seg[--hi]);
 *             }
 *             lo >>= 1;
 *             hi >>= 1;
 *         }
 *         return res;
 *     }
 *
 *     public int lengthOfLIS(int[] A, int k) {
 *         int ans = 0;
 *         for (int i = 0; i < A.length; ++i){
 *             int l = Math.max(0, A[i]-k);
 *             int r = A[i];
 *             int res = query(l, r) + 1; // best res for the current element
 *             ans = Math.max(res, ans);
 *             update(A[i], res); // and update it here
 *         }
 *         return ans;
 *     }
 * }
 */
