import java.util.Arrays;

/**
 * Array of Array Products
 * Given an array of integers arr, you’re asked to calculate for each index i the product
 * of all integers except the integer at that index (i.e. except arr[i]).
 * Implement a function arrayOfArrayProducts that takes an array of integers and returns an array of the products.
 *
 * Solve without using division and analyze your solution's time and space complexities.
 *
 * Examples:
 *
 *
 * input:  arr = [8, 10, 2]
 * output: [20, 16, 80] # by calculating: [10*2, 8*2, 8*10]
 */
public class ArrayOfArrayProducts {
    public static void main(String[] args) {
//        int []input = {8, 10, 2};
        int []input = {1};
        System.out.println(Arrays.toString(arrayOfArrayProducts(input)));
    }

    public static int[] arrayOfArrayProducts(int[] input) {

        if (input == null || input.length == 0 || input.length == 1) {
            return new int[0];
        }
        int sizeOfArray = input.length;
        int[] result = new int[input.length];
        int prev = 1;

        for (int i = 0; i < sizeOfArray; i++) {
            result[i] = prev;
            prev *= input[i];
        }

        prev = 1;
        for (int i = sizeOfArray - 1; i >= 0; i--) {
            result[i] *= prev;
            prev *= input[i];
        }
        return result;
    }
}
