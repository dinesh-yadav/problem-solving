import java.util.Arrays;
import java.util.Stack;

/**
 * Daily Temperatures
 * Solved
 * You are given an array of integers temperatures
 * where temperatures[i] represents the daily temperatures on the ith day.
 *
 * Return an array result where result[i] is the number of days after
 * the ith day before a warmer temperature appears on a future day.
 * If there is no day in the future where a warmer temperature will appear for the ith day, set result[i] to 0 instead.
 *
 * Example 1:
 *
 * Input: temperatures = [30,38,30,36,35,40,28]
 *
 * Output: [1,4,1,2,1,0,0]
 * Example 2:
 *
 * Input: temperatures = [22,21,20]
 *
 * Output: [0,0,0]
 */
public class DailyTemperature {
    public static void main(String[] args) {
        int[] temp = {30,38,30,36,35,40,28}; //{22, 21, 20};//
        System.out.println(Arrays.toString(nextHighTemperature(temp)));
        System.out.println(Arrays.toString(nextHighTemperature1(temp)));
    }

    static int[] nextHighTemperature(int[] temp) {
        int n = temp.length;
        int[] result = new int[n];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && temp[i] > stack.peek()[0]) {
                int[] pair = stack.pop();
                result[pair[1]] = i - pair[1];
            }
            stack.push(new int[]{temp[i], i});
        }
        return result;
    }

    static int[] nextHighTemperature1(int[] temp) {
        int n = temp.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && temp[i] > temp[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }
}
