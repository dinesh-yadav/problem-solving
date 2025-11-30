/**
 * Balanced Binary Tree
 * Solved
 * Given a binary tree, return true if it is height-balanced and false otherwise.
 *
 * A height-balanced binary tree is defined as a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,null,null,4]
 *
 * Output: true
 * Example 2:
 *
 *
 *
 * Input: root = [1,2,3,null,null,4,null,5]
 *
 * Output: false
 * Example 3:
 *
 * Input: root = []
 *
 * Output: true
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 1000].
 * -1000 <= Node.val <= 1000
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        right.right = new TreeNode(4);


        System.out.println(isBalanced(root));
        System.out.println(isBalancedDfs(root));


    }

    //O(n^2)
    static boolean isBalanced(TreeNode node) {
        if (node == null)
            return  true;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        if (Math.abs(leftHeight - rightHeight) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    static int height(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    //O(n)
    static boolean isBalancedDfs(TreeNode node) {
        return dfs(node)[0] == 1;
    }

    static int[] dfs(TreeNode node) {
        if (node == null)
            return new int[]{1, 0};

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        boolean isBalance = (left[0] == 1
                && right[0] == 1
                && Math.abs(left[1] - right[1]) <= 1);
        int height = 1 + Math.max(left[1], right[1]);
        return new int[]{isBalance?1:0, height};
    }
}
