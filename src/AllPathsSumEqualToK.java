import java.util.HashMap;
import java.util.Map;

/**
 * All Paths w/ Sum Equal to K
 * Give a binary tree’s root and a target sum sum,
 * count the total unique paths in the tree that sum to target sum sum.
 *
 * A path can start at the root, interior to the tree, or at a leaf (the path being the leaf node itself).
 *
 * A path can end at the root (the path being the root node itself), interior to the tree, or at a leaf.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 * sum = 8
 * root = [9, -1, 4, 9, null, 3, 4, null, 2]
 *           9
 *         /   \
 *       -1     4
 *       /     / \
 *      9     3   4
 *       \
 *        2
 *
 * Output: 3
 *
 * Explanation: The following are all the paths in the tree with pathsum 8:
 *
 * 9 (root) -> -1 (interior)
 *           9*
 *         /   \
 *       -1*    4
 *       /     / \
 *      9     3   4
 *       \
 *        2
 *
 * 4 (interior) -> 4 (leaf)
 *           9
 *         /   \
 *       -1     4*
 *       /     / \
 *      9     3   4*
 *       \
 *        2
 *
 * -1 (interior) -> 9 (interior)
 *           9
 *         /   \
 *       -1*    4
 *       /     / \
 *      9*    3   4
 *       \
 *        2
 */
public class AllPathsSumEqualToK {
    private static int totalPaths = 0;
    private static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        TreeNode root = new TreeNode(9);
        TreeNode left = new TreeNode(-1);
        TreeNode right = new TreeNode(4);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(9);
        left.left.right = new TreeNode(2);
        right.left = new TreeNode(3);
        right.right = new TreeNode(4);
        int target = 8;
//        System.out.println(allPathsSumEqualToK(root, target));
        System.out.println(allPathsSumEqualToKWithMap(root, target));
    }

    //O(n^2) time and O(n) space
    private static int allPathsSumEqualToK(TreeNode node, int target) {
        if (node == null)
            return 0;
        int rootedPathSum = helper(0, target, node);
        return rootedPathSum + allPathsSumEqualToK(node.left, target) + allPathsSumEqualToK(node.right, target);
    }

    private static int helper(int currentSum, int target, TreeNode node) {
        if (node == null)
            return 0;
        currentSum += node.val;
        int count = target == currentSum?1:0;
        count += helper(currentSum, target, node.left);
        count += helper(currentSum, target, node.right);
        return count;
    }

    //O(n) time and O(n) space
    private static int allPathsSumEqualToKWithMap(TreeNode node, int target) {
        if (node == null)
            return 0;
        map.put(0, 1);
        helperMap(node, target, 0);
        return totalPaths;
    }

    private static void helperMap(TreeNode node, int target, int currentSum) {
        if (node == null)
            return;
        currentSum += node.val;

        int mapKey = currentSum - target;
        totalPaths += map.getOrDefault(mapKey, 0);
        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        helperMap(node.left, target, currentSum);
        helperMap(node.right, target, currentSum);
        map.put(currentSum, map.get(currentSum) - 1);
    }

}
