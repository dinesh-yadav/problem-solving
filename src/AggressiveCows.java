import java.util.Arrays;

/**
 * Given an array stalls[] representing the positions of stalls and an integer k denoting the number of aggressive
 * cows, place the cows in the stalls such that the minimum distance between any two cows is as large as possible.
 * Return this maximum possible minimum distance.
 *
 * Examples:
 *
 * Input: stalls[] = [1, 2, 4, 8, 9], k = 3
 * Output: 3
 * Explanation: We can place cow 1 at position 1, cow 2 at position 4 and cow 3 at position 9.
 * So, the maximum possible minimum distance between two cows is 3.
 *
 * Input: stalls[] = [6, 7,  9, 11, 13, 15], k = 4
 * Output: 2
 * Explanation: We can place cow 1 at position 6, cow 2 at position 9, cow 3 at position 11 and cow 4 at position 15.
 * So, the maximum possible minimum distance between two cows is 2.
 */
public class AggressiveCows {
    public static void main(String[] args) {
        int[] stalls = {6, 7,  9, 11, 13, 15}; // {1, 4, 2, 9, 8};
        int cows = 4; //3;
        System.out.println(minDistance(stalls, cows));
    }

    public static int minDistance(int[] stalls, int cows) {
        Arrays.sort(stalls);
        int result = 0;
        int numOfStalls = stalls.length;
        int low = 1;
        int high = stalls[numOfStalls - 1] - stalls[0];
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(check(stalls, cows, mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static boolean check(int[] stalls, int cows, int dist) {
        int count = 1;
        int prevStall = stalls[0];
        int numOfStalls = stalls.length;
        for (int i = 1; i < numOfStalls; i++) {
            if (stalls[i] - prevStall >= dist) {
                count++;
                prevStall = stalls[i];
            }
        }

        return count >= cows;
    }
}
