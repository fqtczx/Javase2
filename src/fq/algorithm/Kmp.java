package fq.algorithm;

public class Kmp {
    public static void main(String[] args) {
        String str1="i like you i am good people";
        String str2=".";
        System.out.println(violenceMatch(str1,str2));
    }

    public static int violenceMatch(String str1,String str2){
        //使用暴力匹配法，双指针实现
        for(int i=0,j=0;i<str1.length() && j<str2.length();){
            if(str1.charAt(i)==str2.charAt(j)){
                i++;
                j++;
            }else{
                i=i-(j-1);
                j=0;
            }

            if(j==str2.length()){
                return i-j;
            }
        }
        return -1;
    }
}
