import java.util.Arrays;

/**
 * Score Combinations
 * Given an array of point values for potential scoring events in a sport, and a game’s final score, return the total of possible unique score event arrangements that could result in the final score.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 * scoreEvents = [2,3,7]
 * finalScore = 12
 *
 * Output: 4
 *
 * Explanation:
 * There are 4 possible ways that the game ended with a final score of 12:
 * 1.) [2, 2, 2, 2, 2, 2]
 * 2.) [3, 3, 3, 3]
 * 3.) [2, 2, 2, 3, 3]
 * 4.) [2, 3, 7]
 * Example 2
 *
 * Input:
 * scoreEvents = [2,4,5]
 * finalScore = 9
 *
 * Output: 2
 *
 * Explanation:
 * There are 2 possible ways that the game ended with a final score of 9:
 * 1.) [2, 2, 5]
 * 2.) [4, 5]
 * Example 3
 *
 * Input:
 * scoreEvents = [4,5]
 * finalScore = 11
 *
 * Output: 0
 */

public class ScoreCombinations {
    public static void main(String[] args) {
        int[] scoreEvents = {2, 4, 5};
        int finalScore = 9;

        System.out.println(scoreCombinations(scoreEvents, finalScore));
    }

    public static int scoreCombinations(int[] scoreEvents, int finalScore) {
        if (finalScore == 0)
            return 1;
        if (finalScore < 0 || scoreEvents == null)
            return 0;
        int events = scoreEvents.length;
        Integer[][] cache = new Integer[events][finalScore+1];
        return helper(scoreEvents, finalScore, cache, 0);
    }

    public static int helper(int[] scoreEvents, int score, Integer[][] cache, int index) {
        if (score == 0) {
            return 1;
        }
        if (score < 0 || index >= scoreEvents.length) {
            return 0;
        }

        if(cache[index][score] != null)
            return cache[index][score];

        int with = helper(scoreEvents, score - scoreEvents[index], cache, index);
        int without = helper(scoreEvents, score, cache, index+1);
        cache[index][score] = with + without;
        return cache[index][score];
    }


    /*
    This will work if order of events matter.
    public static int scoreCombinations(int[] scoreEvents, int finalScore) {
        int[] cache = new int[finalScore+1];
        Arrays.fill(cache, -1);
        return helper(scoreEvents, finalScore, cache);
    }

    public static int helper(int[] scoreEvents, int score, int[] cache) {
        if (score == 0) {
            return 1;
        }

        if (score < 0) {
            return 0;
        }

        if (cache[score] != -1) {
            return cache[score];
        }

        int combinations = 0;
        for (int scoreEvent : scoreEvents) {
            combinations += helper(scoreEvents, score - scoreEvent, cache);
        }
        cache[score] = combinations;
        return cache[score];
    }

     */
}
