package fq.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr={-2,3,34,45,-43,43,-3};
        int temp=0;
        boolean flag=false;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    flag=true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }

            if(!flag){
                break;
            }else{
                flag=false;
            }
        }

        for(int i:arr){
            System.out.print(i+" ");
        }
        System.out.println(Arrays.toString(arr));
    }
}
