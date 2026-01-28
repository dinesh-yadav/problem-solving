import java.util.Arrays;

/**
 * rearrange array like this
 *  i/p 1 2 3 4 5 6
 *  o/p 1 6 2 5 3 4
 */
public class RearrangeArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6};
        System.out.println(Arrays.toString(rearrangeArray(arr)));
    }

    static int[] rearrangeArray(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        int l = 0;
        int r = n - 1;
        int i = 0;
        while (l <= r) {
            result[i] = arr[l];
            i++;
            if (l != r) {
                result[i] = arr[r];
                i++;
            }
            l++; r--;
        }
        return result;
    }
}
