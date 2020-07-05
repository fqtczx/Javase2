package fq.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr={-2,3,34,45,-43,43,-3};
        selectsort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectsort(int[] arr){
        for(int i=0;i<arr.length;i++){
            int min=arr[i];
            int index=i;
            int temp=arr[i];
            for(int j=i;j<arr.length;j++){
                if(arr[j]<min){
                    min=arr[j];
                    index=j;
                }
            }
            arr[i]=arr[index];
            arr[index]=temp;
        }
    }
}
