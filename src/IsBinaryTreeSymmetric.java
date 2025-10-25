/**
 * Test if a Binary Tree is Symmetric
 * Given a binary tree, test if it is symmetric both in value and in structure.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 *        2
 *      /   \
 *     1     1
 * Output: true
 * Example 2
 *
 * Input:
 *          4
 *        /   \
 *       2     2
 *      / \   /
 *     1   2 2
 * Output: false
 */
public class IsBinaryTreeSymmetric {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(1);
        left.right = new TreeNode(2);
        right.left = new TreeNode(2);
        right.right = new TreeNode(1);
        System.out.println(isBinaryTreeSymmetric(root));
    }

    private static boolean isBinaryTreeSymmetric(TreeNode node) {
        if (node == null)
            return true;
        return isBinaryTreeSymmetricHelper(node.left, node.right);
    }

    private static boolean isBinaryTreeSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left != null && right != null && left.val == right.val) {
            return isBinaryTreeSymmetricHelper(left.left, right.right) &&
                    isBinaryTreeSymmetricHelper(left.right, right.left);
        }
        return false;
    }
}
