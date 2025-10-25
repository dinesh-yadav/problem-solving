/**
 * Lowest Common Ancestor in a BST
 * Given the root of a binary search tree and the value of nodes x and y,
 * return the value of their lowest common ancestor.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 * root = Node{50}
 * x = 1
 * y = 101
 *                   50
 *                 /    \
 *               25     100
 *              /  \   /   \
 *             1   27 80   101
 *
 * Output: 50
 *
 * Explanation: The root is the lowest common ancestor between 1 and 101.
 * It is also the highest common ancestor between these two nodes.
 * Example 2
 */
public class LcaInBst {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);
        TreeNode left = new TreeNode(25);
        TreeNode right = new TreeNode(100);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(1);
        left.right = new TreeNode(27);
        right.left = new TreeNode(80);
        right.right = new TreeNode(101);
        int val1 = 25; //1;
        int val2 = 27; //101;
        System.out.println(lcaInBst(root, val1, val2));
    }

    private static int lcaInBst(TreeNode node, int val1, int val2) {
        if (val1 < node.val && val2 < node.val) {
            return lcaInBst(node.left, val1, val2);
        } else if (val1 > node.val && val2 > node.val) {
            return lcaInBst(node.right, val1, val2);
        } else {
            return node.val;
        }

    }
}
