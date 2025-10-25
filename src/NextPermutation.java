import java.util.Arrays;

/**
 * Next Permutation
 * Given an array of integers, find the next permutation of the sequence.
 * If the provided array is the final permutation of the sequence of integers,
 * return a sorted array.
 *
 * Example Permutation Sequence:
 *
 * 1,2,3
 * 1,3,2
 * 2,1,3
 * 2,3,1
 * 3,1,2
 * 3,2,1
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] input = {2, 3, 5, 4, 1};
        System.out.println(Arrays.toString(nextPermutation(input)));
        int[] in = {3, 2, 1};
        System.out.println(Arrays.toString(nextPermutation(in)));
    }

    public static int[] nextPermutation(int[] input) {
        int i = input.length - 2;
        while(i >= 0 && input[i + 1] <= input[i]) {
            i--;
        }

        int indexToSwap = i;
        if (i >= 0) {
            swapNums(input, indexToSwap);
        }
        reverseNums(input, indexToSwap + 1);
        return input;
    }

    public static void swapNums(int[] input, int index) {
        int len = input.length - 1;
        while (index < len) {
            if (input[index] < input[len]) {
                int tmp = input[index];
                input[index] = input[len];
                input[len] = tmp;
                break;
            }
            len--;
        }
    }

    public static void reverseNums(int[] input, int index) {
        int start = index;
        int end = input.length - 1;
        while (start <= end) {
            int tmp = input[start];
            input[start] = input[end];
            input[end] = tmp;
            start++;
            end--;
        }
    }
}
