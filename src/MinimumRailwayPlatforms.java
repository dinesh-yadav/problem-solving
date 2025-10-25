import java.util.Arrays;

/**
 * Minimum Railway Platforms
 * You are given the arrival & departure times of trains running through a railway station, arr & dep.
 *
 * Return the minimum number of platforms required for the station so that no train is left waiting to enter a terminal.
 *
 * Interval overlap will be inclusive on both arrival & departure. train x with arrival &
 * departure → […, “9:40”] overlaps with train y with arrival & departure → [”9:40”, …]
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 * arr = ["9:00", "9:40", "9:50", "11:00", "15:00", "18:00"]
 * dep = ["9:10", "12:00", "11:20", "11:30", "19:00", "20:00"]
 *
 * Output: 3
 *
 * Explanation:
 * - Train 0 needs a platform.
 * - Train 1, 2, & 3 intersect (train 1 arrives at 9:40 then stays until 12:00,
 * train 2 arrives at 9:50, causing an intersect/wait behind train 1.
 * Then train 3 would sit behind train 2 who is waiting for train 1)
 * So we notice that at any 1 point in time, we can have 3 trains through the station.
 * - Train 4 & 5's times overlap, but since we know we need 3 platforms, no train waits.
 */
public class MinimumRailwayPlatforms {
    public static void main(String[] args) {
        String[] arr = {"9:00", "9:40", "9:50", "11:00", "15:00", "18:00"};
        String[] dep = {"9:10", "12:00", "11:20", "11:30", "19:00", "20:00"};
        System.out.println("###################################");
        System.out.println(minPlatforms(arr, dep));

        System.out.println("###################################");
        System.out.println(minPlatformsCache(arr, dep));
    }

    // O(nlog(n))
    public static int minPlatforms(String[] arr, String[] dep){
        int minCount = 0;
        int count = 0;
        int arrIndex = 0;
        int depIndex = 0;

        int[] arrTimes = new int[arr.length];
        int[] depTimes = new int[dep.length];
        for (int i = 0; i < arr.length; i++) {
            arrTimes[i] = convertToInt(arr[i]);
            depTimes[i] = convertToInt(dep[i]);
        }

        Arrays.sort(arrTimes);
        Arrays.sort(depTimes);

        while(arrIndex < arr.length && depIndex < dep.length){
            if (arrTimes[arrIndex] <= depTimes[depIndex]) {
                count++;
                arrIndex++;
                minCount = Math.max(minCount, count);
            } else {
                count--;
                depIndex++;
            }
        }
        return minCount;
    }

    //O(n) as we will have 24*60=1440 timeline
    public static int minPlatformsCache(String[] arr, String[] dep) {
        int[] arrTimes = new int[arr.length];
        int[] depTimes = new int[dep.length];
        for (int i = 0; i < arr.length; i++) {
            arrTimes[i] = convertToInt(arr[i]);
            depTimes[i] = convertToInt(dep[i]);
        }

        int size = 24*60;
        int[] arrMap = new int[size];
        int[] depMap = new int[size];
        for (int i = 0; i < arrTimes.length; i++) {
            arrMap[arrTimes[i]]++;
            depMap[depTimes[i]]++;
        }

        int minPlatform = 0;
        int platforms = 0;
        for (int i = 0; i < size; i++) {
            platforms += (arrMap[i] - depMap[i]);
            minPlatform = Math.max(platforms, minPlatform);
        }
        return minPlatform;
    }

    public static int convertToInt(String str) {
        int indexOfColan = str.indexOf(":");
        return Integer.parseInt(str.substring(0,indexOfColan))*60 +
                Integer.parseInt(str.substring(indexOfColan+1,indexOfColan+3));
    }
}