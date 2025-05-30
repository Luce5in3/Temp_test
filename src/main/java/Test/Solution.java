package Test;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; ; i++ ){
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }

            map.put(nums[i],i);
        }
    }
}
