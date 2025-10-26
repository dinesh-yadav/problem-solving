/**
 * Subtree of Another Tree
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the
 * same structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree is a tree that consists of a node in tree and all of this node's descendants.
 * The tree could also be considered as a subtree of itself.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5], subRoot = [2,4,5]
 *
 * Output: true
 * Example 2:
 * Input: root = [1,2,3,4,5,null,null,6], subRoot = [2,4,5]
 *
 * Output: false
 * Constraints:
 *
 * 1 <= The number of nodes in both trees <= 100.
 * -100 <= root.val, subRoot.val <= 100
 */
public class SubTreeOfAnotherTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);

        TreeNode subRoot = new TreeNode(2);
        TreeNode leftSubRoot = new TreeNode(4);
        TreeNode rightSubRoot = new TreeNode(5);
        subRoot.left = leftSubRoot;
        subRoot.right = rightSubRoot;
        System.out.println(subTreeOfAnotherTree(root, subRoot));
    }

    public static boolean subTreeOfAnotherTree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null)
            return true;
        if (root == null)
            return false;

        if (isSameTree(root, subRoot)) {
            return true;
        }
        return subTreeOfAnotherTree(root.left, subRoot) ||
                subTreeOfAnotherTree(root.right, subRoot);
    }

    public static boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 != null && root2 != null && root1.val == root2.val) {
            return isSameTree(root1.left, root2.left) &&
                    isSameTree(root1.right, root2.right);
        }
        return false;
    }
}
