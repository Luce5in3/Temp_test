package Test;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {


    public int subarraySum(int[] nums, int k) {
        //构造一个前缀和数组
        int n = nums.length;
        int s[] = new int[n + 1];
        //HashMap 用作统计出现过和为k 的子数组个数
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];

        }
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int sj : s) {
            //我们遍历每个 sj = s[j+1]，在哈希表 cnt 中统计出现过的前缀和 s[i] 的数量。
            ans += map.getOrDefault(sj - k, 0);
            map.merge(sj, 1, Integer::sum);
        }
        return ans;
        /*
        s[j+1] - s[i] == k
        => s[i] == s[j+1] - k
        */
    }


}
