package fq.leetcode202;

import javax.swing.plaf.metal.MetalTheme;

public class Solution202 {
    public static void main(String[] args) {
//        System.out.println((int)Math.floor(Math.log10(130)+1));
        System.out.println(isHappy(19));
    }

    public static boolean isHappy(int n) {
        boolean flag=false;
        for(int j=0;j<10;j++){
            int length=(int)Math.floor(Math.log10(n)+1);
            int[] res=new int[length];
            int num=0;

            for(int i=0;i<length;i++){
                res[i]=(int)(n/(Math.pow(10,i))%10);

//                System.out.println(res[i]);
            }

            for(int i=0;i<length;i++){
                num=num+(int)(Math.pow(res[i],2));
            }
            n=num;
            if(n==1){
                flag=true;
                break;
            }
        }
        return flag;
    }
}
