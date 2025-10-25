/**
 * Binary Tree Span
 * Given the root node of a binary tree, determine the longest path that exists between any two nodes in the tree.
 *
 * Input-Output
 *
 * Example 1
 * Input:
 *     1
 *
 * Output: 0
 *
 * Explanation: The tree has a single node with no edges connecting it to another node.
 * No longest node-to-node path can exist with only one node.
 * Example 2
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *      /  \
 *     4    5
 *    /    / \
 *   6    7   8
 *    \
 *     9
 *
 * Output: 5
 *
 * Explanation:
 * Longest Paths
 * [9,6,4,3,1,2]
 * [9,6,4,3,5,7]
 * [9,6,4,3,5,8]
 *
 * Any one of these would be a valid longest path in the binary tree. The length of all these
 * paths is 5. Notice that the root node is not guaranteed to be on the longest path.
 */
public class BinaryTreeSpan {
    private static int maximum = 0;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
//        left.left = new TreeNode(1);
//        left.right = new TreeNode(2);
        right.left = new TreeNode(4);
        right.right = new TreeNode(5);
        right.left.left = new TreeNode(6);
        right.left.left.right = new TreeNode(9);
        right.right.left = new TreeNode(7);
        right.right.right = new TreeNode(8);
        binaryTreeSpan(root);
        System.out.println(maximum);
        maximum = 0;
        TreeNode root1 = new TreeNode(1);
        binaryTreeSpan(root1);
        System.out.println(maximum);
    }

    public static int binaryTreeSpan(TreeNode node) {
        if (node == null)
            return 0;

        int leftHeight = binaryTreeSpan(node.left);
        int rightHeight = binaryTreeSpan(node.right);
        int height = 1 + Math.max(leftHeight, rightHeight);

        maximum = Math.max(maximum, leftHeight + rightHeight);
        return height;

    }
}
