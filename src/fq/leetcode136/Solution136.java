package fq.leetcode136;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Solution136 {
    public static void main(String[] args) {
        int[] nums={4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }
    public static int singleNumber(int[] nums) {
        HashMap<Integer,Integer> ic=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(ic.get(nums[i])==null){
                ic.put(nums[i],1);
            }else{
                ic.remove(nums[i]);
            }
        }
        Set<Integer> ks =ic.keySet();
        ArrayList list=new ArrayList(ks);
        return (int) list.get(0);
    }
}
