import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 95. Unique Binary Search Trees II
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 * Example 1:
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 * Input: n = 1
 * Output: [[1]]
 * Constraints:
 * 1 <= n <= 8
 */
public class UniqueBsts2 {
    public static void main(String[] args) {
        List<TreeNode> result1 = uniqueBsts(3);
        for (TreeNode t : result1) {
            t.levelOrderTraversal();
            System.out.println();
        }
        List<TreeNode> result2 = uniqueBsts(5);
        for (TreeNode t : result2) {
            t.levelOrderTraversal();
            System.out.println();
        }
    }

    static List<TreeNode> uniqueBsts(int n) {
        if (n == 0)
            return new ArrayList<>();
        Map<String, List<TreeNode>> memo = new HashMap<>();
        return helper(1, n, memo);
    }

    static List<TreeNode> helper(int start, int end, Map<String, List<TreeNode>> memo) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }

        String key = start + ":" + end;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1, memo);
            List<TreeNode> right = helper(i + 1, end, memo);

            for (TreeNode l : left) {
                for (TreeNode r: right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        }
        memo.put(key, result);
        return result;
    }
}
