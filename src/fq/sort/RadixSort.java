package fq.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr={3,34,45,78,6,135,32,43};
        radixSort(arr);
//        System.out.println(Arrays.toString(arr));
        radixSort1(arr);
    }

    public static void radixSort(int[] arr){
        //第一轮排序
        int[][] bucket=new int[10][arr.length];
        int[] indexs=new int[10];
        for(int j=0;j<arr.length;j++){
            int ele=arr[j]%10;
            bucket[ele][indexs[ele]]=arr[j];
            indexs[ele]++;
        }
        int index=0;
        for(int k=0;k<10;k++){
            if(indexs[k]!=0){
                for(int l=0;l<indexs[k];l++){
                    arr[index]=bucket[k][l];
                    index++;
                }
            }
            indexs[k]=0;
        }

        //第二轮排序
        for(int j=0;j<arr.length;j++){
            int ele=arr[j]/10%10;
            bucket[ele][indexs[ele]]=arr[j];
            indexs[ele]++;
        }
        index=0;
        for(int k=0;k<10;k++){
            if(indexs[k]!=0){
                for(int l=0;l<indexs[k];l++){
                    arr[index]=bucket[k][l];
                    index++;
                }
            }
            indexs[k]=0;
        }

        //第三轮排序
        for(int j=0;j<arr.length;j++){
            int ele=arr[j]/10/10%10;
            bucket[ele][indexs[ele]]=arr[j];
            indexs[ele]++;
        }
        index=0;
        for(int k=0;k<10;k++){
            if(indexs[k]!=0){
                for(int l=0;l<indexs[k];l++){
                    arr[index]=bucket[k][l];
                    index++;
                }
            }
            indexs[k]=0;
        }
    }

    public static void radixSort1(int[] arr){
        int max=arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }

        int length=(max+"").length();

        int[][] bucket=new int[10][arr.length];
        int[] indexs=new int[10];

        for(int i=0;i<length;i++){
            for(int j=0;j<arr.length;j++){
                int ele= (int) (arr[j]/Math.pow(10,i)%10);
                bucket[ele][indexs[ele]]=arr[j];
                indexs[ele]++;
            }
            int index=0;
            for(int k=0;k<10;k++){
                if(indexs[k]!=0){
                    for(int l=0;l<indexs[k];l++){
                        arr[index]=bucket[k][l];
                        index++;
                    }
                }
                indexs[k]=0;
            }
            System.out.print("第"+(i+1)+"次排序结果为");
            System.out.println(Arrays.toString(arr));
        }
    }
}
