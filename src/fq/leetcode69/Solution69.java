package fq.leetcode69;

public class Solution69 {
    public static void main(String[] args) {
        System.out.println(mySqrt(2147395600));
    }

    public static int mySqrt(int x) {
        for(int i=1;i <= x/2+1;i++){
            if(i*i<=x &&(i+1)*(i+1)>x){
                return i;
            }
        }
        return 0;
    }
}
