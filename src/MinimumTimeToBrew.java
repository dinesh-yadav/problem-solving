import java.util.Arrays;

/*
You are given two integer arrays, skill and mana, of length n and m, respectively.

In a laboratory, n wizards must brew m potions in order.
Each potion has a mana capacity mana[j] and must pass through all the wizards sequentially to be brewed properly.
 The time taken by the ith wizard on the jth potion is timeij = skill[i] * mana[j].

Since the brewing process is delicate, a potion must be passed to the next wizard
immediately after the current wizard completes their work.
 This means the timing must be synchronized
 so that each wizard begins working on a potion exactly when it arrives.

Return the minimum amount of time required for the potions to be brewed properly.

 Example 1:

Input: skill = [1,5,2,4], mana = [5,1,4,2]

Output: 110

Explanation:

Potion Number	Start time	Wizard 0 done by	Wizard 1 done by	Wizard 2 done by	Wizard 3 done by
0	0	5	30	40	60
1	52	53	58	60	64
2	54	58	78	86	102
3	86	88	98	102	110
As an example for why wizard 0 cannot start working on the 1st potion before time t = 52,
consider the case where the wizards started preparing
the 1st potion at time t = 50. At time t = 58, wizard 2 is done with the 1st potion,
but wizard 3 will still be working on the 0th potion till time t = 60.

Example 2:

Input: skill = [1,1,1], mana = [1,1,1]

Output: 5

Explanation:

Preparation of the 0th potion begins at time t = 0, and is completed by time t = 3.
Preparation of the 1st potion begins at time t = 1, and is completed by time t = 4.
Preparation of the 2nd potion begins at time t = 2, and is completed by time t = 5.
Example 3:

Input: skill = [1,2,3,4], mana = [1,2]

Output: 21



Constraints:

n == skill.length
m == mana.length
1 <= n, m <= 5000
1 <= mana[i], skill[i] <= 5000
 */
public class MinimumTimeToBrew {
    public static void main(String[] args) {
        int[] skill = {1,5,2,4};
        int[] mana = {5,1,4,2};
        System.out.println(solution(skill, mana));

    }

    public static int solution(int[] skill, int[] mana) {
        int wizards = skill.length;
        int potions = mana.length;
        int [][]time = new int[potions][wizards + 1];
        time[0][0] = 0;
        for (int i = 1; i <= wizards; i++) {
            time[0][i] = time[0][i-1] + skill[i - 1]*mana[0];
        }
        System.out.println(Arrays.toString(time[0]));

        for (int i = 1; i < potions; i++) {
            int endTime = time[i-1][wizards] + skill[wizards - 1]*mana[i];
            time[i][wizards] = endTime;
            time[i][wizards - 1] = time[i-1][wizards];
            for (int j = wizards - 2; j >= 0; j--) {
                endTime = time[i][j + 1] - skill[j]*mana[i];
                if (endTime >= time[i-1][j+1]) {
                    time[i][j] = endTime;
                } else {
                    // update the whole row
                    int up = time[i-1][j+1] - endTime;
                    for (int k = j+1; k < wizards+1; k++) {
                        time[i][k] = time[i][k] + up;
                    }
                    time[i][j] = time[i-1][j+1];
                }
            }
            System.out.println(i + " " + Arrays.toString(time[i]));
        }
        return time[potions - 1][wizards];
    }

}

class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length;
        long[] done = new long[n + 1];

        for (int j = 0; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                done[i + 1] = Math.max(done[i + 1], done[i]) + (long) mana[j] * skill[i];
            }
            for (int i = n - 1; i > 0; --i) {
                done[i] = done[i + 1] - (long) mana[j] * skill[i];
            }
        }

        return done[n];
    }
}
