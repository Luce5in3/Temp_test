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

    /**
     * 5.三数之和
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int x = nums[i];
            if( i > 0 && x == nums[i - 1] ){
                continue;
            }
            int j = i + 1 , k = nums.length - 1;

            while (j < k) {
                int s = nums[i] + nums[j] + nums[k];
                if(s > 0){
                    k-- ;
                }else if(s < 0){
                    j++ ;
                }else {
                    res.add(List.of(x,nums[j],nums[k]));
                    for (j++; j < k && nums[j] == nums[j - 1]; j++); // 跳过重复数字
                    for (k--; k > j && nums[k] == nums[k + 1]; k--); // 跳过重复数字
                }

            }
        }
        return res;
    }

    /**
     * 6.盛水最多的容器
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int sum = 0;
        int i = 0;
        int n= height.length -1;

        while(i<n){
            int area =(n - i) *Math.min(height[i],height[n]);
            sum = Math.max(area,sum);

            if(height[i]<height[n]){
                i++;
            }else
            {
                n--;
            }
        }
        return sum;
    }

    /**
     * 7.接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n =height.length;
        int left = 0;
        int right = n-1;

        int premax=0;
        int sufmax=0;
        int ans =0;

        while(left<right){
            premax= Math.max(premax,height[left]);
            sufmax= Math.max(sufmax,height[right]);
            ans +=premax < sufmax ? premax-height[left++] : sufmax -height[right--];
        }
        return ans;
    }

    /**
     * 8.最长无重复子字符串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] a = s.toCharArray();
        int left =0 ;
        int ans =0 ;
        int n =s.length();
        boolean [] has = new boolean[128];
        for(int right =0 ;right<n ;right++){
            char c = a[right];
            while(has[c]){
                //使用的是ASIIC码进行对于出现重复的进行标记
                has[a[left]]=false;
                left ++;
            }
            has[c]=true;
            ans = Math.max(ans,right-left+1);
        }
        return ans;
    }

    /**
     * 9.找到字符串种的所有字母异位词
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int slen= s.length() , plen = p.length();
        List<Integer> ans = new ArrayList<>();

        if(slen < plen){
            return new ArrayList<>();
        }

        int [] snums = new int[26];
        int [] pnums = new int[26];

        for (int i = 0; i < plen ; i++) {
            //初始化字符
            ++snums[s.charAt(i)-'a'];
            ++pnums[p.charAt(i)-'a'];
        }
        if(Arrays.equals(snums,pnums)){
            ans.add(0);
        }

        for (int i = 0; i < slen - plen; i++) {
            --snums[s.charAt(i)-'a'];
            ++snums[s.charAt(i+plen)-'a'];
            if(Arrays.equals(snums,pnums)){
                ans.add(i+1);
            }
        }
        return ans;
    }
}
