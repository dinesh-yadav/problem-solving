/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 *
 * Constraints:
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 *
 * Followup
 * Suppose a '0' in the input means that there is a leak at that position and the water can leak out.
 * After the adjustment, that is, after the water levels have stabilized due to leaking, what is the answer?
 *
 * How do we change our approach/what would be out ideal answer for this scenario?
 *
 */
public class TrapRainWater {
    public static void main(String[] args) {
       // int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] heights = {4,2,0,3,2,5};
        System.out.println(trappedRainWater(heights));
    }

    public static int trappedRainWater(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int leftMax = heights[left];
        int rightMax = heights[right];
        int water = 0;

        while(left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, heights[left]);
                water += leftMax - heights[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, heights[right]);
                water += rightMax - heights[right];
            }
        }
        return water;
    }
}
