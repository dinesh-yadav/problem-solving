import java.util.ArrayList;
import java.util.List;

public class MorrisTraversalOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        right.left = new TreeNode(4);
        right.right = new TreeNode(5);
        right.left.left = new TreeNode(6);
        right.left.left.right = new TreeNode(9);
        right.right.left = new TreeNode(7);
        right.right.right = new TreeNode(8);

        System.out.println(inOrderMorrisTraversal(root));
        System.out.println(preOrderMorrisTraversal(root));
    }

    private static List<Integer> inOrderMorrisTraversal(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            // if left is null, then put the node
            // in inOrder list
            if (curr.left == null) {
                inOrder.add(curr.val);
                curr = curr.right;
            } else {
                // go to right mode element
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    //connect right most element to root
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    // break the above connection when traversed.
                    prev.right = null;
                    inOrder.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return inOrder;
    }

    private static List<Integer> preOrderMorrisTraversal(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                preOrder.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                while(prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = curr;
                    preOrder.add(curr.val);
                    curr = curr.left;
                } else {
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return preOrder;
    }
}
