package normal_Demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
    public String firstChar(String s){
        char[] chars = s.toCharArray();
        int gap = 0;
        String res = "";
        for (int i = 0; i < chars.length; i+=gap) {
            res += chars[i];
            gap ++;
        }
        return res;
    }
}