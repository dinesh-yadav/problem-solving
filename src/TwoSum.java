import java.util.*;

public class TwoSum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5};
//        int[] arr = {1, 2, 3, 4, 5, 6};
        int target = 6;
        System.out.println(pairSums1(arr, target));
    }

    static List<List<Integer>> pairSums1(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = target - arr[i];
            if (map.containsKey(key)) {
                result.add(Arrays.asList(arr[i], key));
                map.put(key, map.get(key) - 1);
                if (map.get(key) == 0) {
                    map.remove(key);
                }
            }
            else {
                map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            }
        }
        return result;
    }
}
