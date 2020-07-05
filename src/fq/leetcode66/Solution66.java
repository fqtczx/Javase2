package fq.leetcode66;

import java.util.Arrays;

public class Solution66 {
    public int[] plusOne(int[] digits) {
        boolean[] w=new boolean[digits.length];
        if(digits.length==1){
            digits[0]=digits[0]+1;
            if(digits[0]==10){
                digits[0]=0;
                int[] digit=new int[digits.length+1];
                digit[0]=1;
//                System.arraycopy(digits,0,digit,1,digits.length);
                return digit;
            }else{
                return digits;
            }
        }
        for(int i=digits.length-1;i>=0;i--){
            if(i==digits.length-1){
                digits[i]=digits[i]+1;
                if(digits[i]==10){
                    digits[i]=0;
                    w[i-1]=true;//代表前一位需要进位
                }
            }else if(i==0){
                if(w[i]==true){
                    digits[i]=digits[i]+1;
                    if(digits[i]==10){
                        digits[i]=0;
                        int[] digit=new int[digits.length+1];
                        digit[0]=1;
                        System.arraycopy(digits,0,digit,1,digits.length);
                        return digit;
                    }
                }
            }else{
                if(w[i]==true){
                    digits[i]=digits[i]+1;
                    if(digits[i]==10){
                        digits[i]=0;
                        w[i-1]=true;//代表前一位需要进位
                    }
                }
            }

        }
        return digits;
    }
}
