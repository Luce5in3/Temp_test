package Test;

import java.util.*;

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

    /**
     * 2.字母异位词
     * @param strs
     * @return
     */
    //为什么使用 String.valueOf(char[]) 而不是 toString() 方法因为返回的引用，而String.valueOf(char[]) 返回的是 字符串。
    public List<List<String>> groupAnagrams(String[] strs) {
        //创建了一个map
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            //遍历字符数组中的所有，字符串转化为字符数组。
            char[] charArray = str.toCharArray();
            //字符串转化的数组排序
            //排序之后，只要是 字母异位词 那么得到的字符数组就是一样的
            Arrays.sort(charArray);
            //如果包含了 这个字母异位词 那么就加入 这个词的map 对应的list 中
            if(map.containsKey(String.valueOf(charArray))){
                map.get(String.valueOf(charArray)).add(str);
            }else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(String.valueOf(charArray), list);
            }

        }
        return new ArrayList<>(map.values());
    }

    /**
     * 3.最长子序列
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int max = 0 ;
        Set <Integer>set = new HashSet();
        //转为 set 集合防止出现重复数字，因为最长计算的是不同的元素组成的最长 看样例三
        for (int num : nums) {
            set.add(num);
        }

        for (int s : set) {
            //如果有这个元素的下一个元素继续向下遍历。找到最小值
            if(set.contains(s-1)){
                continue;
            }
            //如果有这个元素的上一个元素继续遍历。
            int y = s+1 ;
            while(set.contains(y)){
                y++;
            }
            //s 从最小值开始 向最大值走。
            max = Math.max(max,y-s);
        }
        return max;
    }

    /**
     * 4.移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int  j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(!(nums[i]==0)){
                //找到非0 元素交换位置
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                //维护下一个非零元素应该在的位置。
                j++;
            }
        }
    }
}
