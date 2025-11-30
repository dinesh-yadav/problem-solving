import java.util.Arrays;

/**
 * Koko Eating Bananas
 * Solved
 * You are given an integer array piles where piles[i] is the number of bananas in the ith pile. You are also given an integer h, which represents the number of hours you have to eat all the bananas.
 *
 * You may decide your bananas-per-hour eating rate of k. Each hour, you may choose a pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, you may finish eating the pile but you can not eat from another pile in the same hour.
 *
 * Return the minimum integer k such that you can eat all the bananas within h hours.
 *
 * Example 1:
 *
 * Input: piles = [1,4,3,2], h = 9
 *
 * Output: 2
 * Explanation: With an eating rate of 2, you can eat the bananas in 6 hours. With an eating rate of 1, you would need 10 hours to eat all the bananas (which exceeds h=9), thus the minimum eating rate is 2.
 *
 * Example 2:
 *
 * Input: piles = [25,10,23,4], h = 4
 *
 * Output: 25
 * Constraints:
 *
 * 1 <= piles.length <= 1,000
 * piles.length <= h <= 1,000,000
 * 1 <= piles[i] <= 1,000,000,000
 */
public class EatingBanana {
    public static void main(String[] args) {
        int[] piles = {1,4,3,2}; //{25, 10, 23, 4};
        int h = 9;
        System.out.println(eatingRate(piles, h));
    }

    static int eatingRate(int[] piles, int h) {
        int min = 1;
        int max = Arrays.stream(piles).max().orElseThrow();
        int minRate = Integer.MAX_VALUE;
        while(min <= max) {
            int mid = min + (max - min)/2;
            long time = calculateTime(mid, piles);
            if (time <= h) {
                minRate = Math.min(mid, minRate);
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return minRate;
    }

    static long calculateTime(int rate, int[] piles) {
        long time = 0;
        for (int num: piles) {
            time += (num + rate - 1)/rate;
        }
        return time;
    }
}
