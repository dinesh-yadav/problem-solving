import java.util.Stack;

/**
 * Largest Rectangle In Histogram
 * You are given an array of integers heights where heights[i] represents the height of a bar.
 * The width of each bar is 1.
 *
 * Return the area of the largest rectangle that can be formed among the bars.
 *
 * Note: This chart is known as a histogram.
 *
 * Example 1:
 *
 * Input: heights = [7,1,7,2,2,4]
 *
 * Output: 8
 * Example 2:
 *
 * Input: heights = [1,3,7]
 *
 * Output: 7
 * Constraints:
 *
 * 1 <= heights.length <= 1000.
 * 0 <= heights[i] <= 1000
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights = {1, 3, 7}; //{7,1,7,2,2,4};
        System.out.println(largestRectangle(heights));
    }

    static int largestRectangle(int[] heights) {
        int n = heights.length;
        if (n == 0)
            return n;
        int area = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n)?0:heights[i];
            while(!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                // 1. Pop the index of the bar whose area we are calculating
                int height = heights[stack.pop()];
                int width = 0;
                if (stack.isEmpty()) {
                    // If the stack is empty, it means this bar extends to the far left (index 0).
                    // Right boundary is 'i' (exclusive), Left boundary is -1 (exclusive).
                    width = i;
                } else {
                    // Right boundary is 'i' (exclusive), Left boundary is stack.peek() (exclusive).
                    // The width is the distance between the two bounding indices minus 1.
                    width = i - stack.peek() - 1;
                }
                area = Math.max(area, height * width);
            }
            stack.push(i);
        }

        return area;
    }
}
