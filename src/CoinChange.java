/**
 * Given an array of integers that represent coin values and an integer amount,
 * return the minimum amount of coins it requires to make complete change for the amount.
 * If it is not possible to make complete change, return -1.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 * coins = [1, 2, 3]
 * amount = 10
 * Output: 4
 * Explanation: We can use 2 "3 coins" & 2 "2 coins" to fully make change for 10 (3 + 3 + 2 + 2 = 10).
 *
 */

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int amount = 10;
        System.out.println(minCoins(coins, amount));
    }

    private static int minCoins(int[] coins, int amount) {
        int[] cache = new int[amount + 1];
        return helper(coins, amount, cache);
    }

    private static int helper(int[] coins, int amount, int[] cache) {
        if (amount == 0)
            return 0;
        if (amount < 0)
            return -1;

        if (cache[amount] != 0) {
            return cache[amount];
        }

        int minCoin = Integer.MAX_VALUE;
        for (int coin : coins) {
            int tmp = helper(coins, amount - coin, cache);
            if (tmp >= 0 && tmp < minCoin) {
                minCoin = 1 + tmp;
            }
        }

        cache[amount] = minCoin == Integer.MAX_VALUE? -1: minCoin;
        return cache[amount];
    }
}
