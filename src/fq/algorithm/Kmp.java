package fq.algorithm;

import java.util.Arrays;

public class Kmp {
    public static void main(String[] args) {
        String str1="i like you i am good people";
        String str2="peo";
        System.out.println(violenceMatch(str1,str2));
        System.out.println(KMP(str1,str2));

        int[] next=next("ABCDABD");//[0,1]
        System.out.println(Arrays.toString(next));
        System.out.println(kmp(str1,str2,next(str2)));
    }

    public static int violenceMatch(String str1,String str2){
        //使用暴力匹配法，双指针实现
        for(int i=0,j=0;i<str1.length() && j<str2.length();){
            //同时使用i和j两个指针进行遍历比较
            if(str1.charAt(i)==str2.charAt(j)){//当在某个位置有相同的字符时
                i++;
                j++;//两个同时向后移动
            }else{//在某个位置不匹配时
                i=i-(j-1);//i要移动到一开始匹配的位置下一个位置
                j=0;//j就要回到小串开头的位置重新开始
            }

            if(j==str2.length()){//当j移动到小串的最后一个位置，说明已经匹配到
                return i-j;//返回在大串中出现的开始索引
            }
        }
        return -1;//没找到返回-1
    }

    //获取一个字符串(子串)的部分匹配值
    public static int[] next(String s){
        int[] next=new int[s.length()];
        next[0]=0;//长度为1的话，部分匹配值就是1
        for(int i=1,j=0;i<s.length();i++){
            //s.charAt(i)!=s.charAt(j)，需要从next[j-1]获取新的j
            //知道我们发现有s.charAt(i)==s.charAt(j)满足时，才退出
            //这是KMP算法的核心思想
            while (j>0 && s.charAt(i) !=s.charAt(j)){
                j=next[j-1];
            }

            //s.charAt(i)==s.charAt(j)满足时，部分匹配值就是+1
            if(s.charAt(i)==s.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }

    //kmp算法
    public static int kmp(String ts, String ps,int[] next){
        for(int i=0,j=0;i<ts.length();i++){
            if(ts.charAt(i)==ps.charAt(j)){
                j++;
            }else{
                while(j>0 && ts.charAt(i)!=ps.charAt(j)){
                    j=next[j-1];
                }
            }

            if(j==ps.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    /*
    下面的是网上的代码
     */
    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static int KMP(String ts, String ps) {
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();
        int i = 0; // 主串的位置
        int j = 0; // 模式串的位置
        int[] next = getNext(ps);
        while (i < t.length && j < p.length) {
            if (j == -1 || t[i] == p[j]) { // 当j为-1时，要移动的是i，当然j也要归0
                i++;
                j++;
            } else {
                // i不需要回溯了
                // i = i - j + 1;
                j = next[j]; // j回到指定位置
            }
        }
        if (j == p.length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
