/**
 * Test a Tree for the BST Property
 * Given a binary tree, return true if it is a binary search tree, otherwise, return false.
 *
 * BST Definition
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Input-Output
 *
 * Example 1
 *
 * Input (level-order serialization): [2, 1, 4, null, null, 3, 5]
 * Output: true
 * Explanation:
 *       2
 *      / \
 *     1   4
 *        / \
 *       3   5
 *
 * 1 < 2    ✓
 * 4 >= 2   ✓
 * 3 >= 2   ✓
 * 3 < 4    ✓
 * 5 >= 2   ✓
 * 5 >= 4   ✓
 */
public class IsBinaryTreeBst {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        root.left = left;
        root.right = right;
//        left.left = new TreeNode(1);
//        left.right = new TreeNode(2);
        right.left = new TreeNode(3);
        right.right = new TreeNode(5);
        System.out.println(isBinaryTreeBst(root));
    }

    private static boolean isBinaryTreeBst(TreeNode node) {
        if (node == null)
            return true;
        return isBinaryTreeBstHelper(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBinaryTreeBstHelper(TreeNode node, int min, int max) {
        if (node == null)
            return true;

        if (node.val <= min || node.val >= max) {
            return false;
        }

        return isBinaryTreeBstHelper(node.left, min, node.val) &&
                isBinaryTreeBstHelper(node.right, node.val, max);
    }
}
