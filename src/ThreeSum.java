import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -2, -3};
        System.out.println(threeSum(nums));
    }

    static public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            int a = nums[i];
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int bi = i + 1;
            int ci = n - 1;
            while (bi < ci) {
                int sum = a + nums[bi] + nums[ci];
                if (sum == 0) {
                    result.add(Arrays.asList(a, nums[bi], nums[ci]));
                    while (bi < ci && nums[bi] == nums[bi + 1]) {
                        bi++;
                    }
                    while (bi < ci && nums[ci] == nums[ci - 1]) {
                        ci--;
                    }
                    bi++;
                    ci--;
                } else if(sum < 0) {
                    bi++;
                } else {
                    ci--;
                }
            }

        }
        return result;
    }
}
