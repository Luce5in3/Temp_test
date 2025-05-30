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
}
