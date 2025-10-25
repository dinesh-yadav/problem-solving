/**
 * Build a Min-Height BST from a Sorted Array
 * Given a sorted array, create a binary search tree with minimal height.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input: [1, 2, 3, 4, 5, 6, 7]
 * Output (level-order serialization): [4, 2, 6, 1, 3, 5, 7]
 * Explanation:
 *          4
 *        /   \
 *       2     6
 *      / \   / \
 *     1   3  5  7
 */
public class CreateMinHeightBstFromSortedArray {
    public static void main(String[] args) {
        int[] sortedArr = {1,2,3,4,5,6,7};
        TreeNode root = createMinHeightBst(sortedArr);
        if (root != null) {
            root.printTree();
        }
    }

    private static TreeNode createMinHeightBst(int[] array) {
        if (array.length == 0)
            return null;
        return createMinHeightBstHelper(array, 0, array.length - 1);
    }

    private static TreeNode createMinHeightBstHelper(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start)/2;
        TreeNode node = new TreeNode(array[mid]);
        node.left = createMinHeightBstHelper(array, start, mid - 1);
        node.right = createMinHeightBstHelper(array, mid + 1, end);
        return node;
    }
}
