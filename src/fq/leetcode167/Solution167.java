package fq.leetcode167;

public class Solution167 {
    public static void main(String[] args) {

    }
    public int[] twoSum(int[] numbers, int target) {
        int index1=0;
        int index2=numbers.length-1;
        while(index1<index2){
            if(numbers[index1]+numbers[index2]==target){
                break;
            }else if(numbers[index1]+numbers[index2]<target){
                index1++;
            }else{
                index2--;
            }
        }

        int[] result=new int[2];
        result[0]=index1+1;
        result[1]=index2+1;
        return result;
    }
}
