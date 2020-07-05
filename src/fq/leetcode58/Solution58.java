package fq.leetcode58;

public class Solution58 {
    public static void main(String[] args) {
        String[] sr=" ".split(" ");
        System.out.println(sr.length);
    }
    public static int lengthOfLastWord(String s) {
        if(s==" "){
            return 0;
        }
        String[] sr=s.split(" ");
        return sr.length==0?0:sr[sr.length-1].length();

    }
}
