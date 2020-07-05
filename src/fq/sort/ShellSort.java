package fq.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr={-2,3,34,45,78,135,32,-43,43,-3};
        int j=5;
        j-=6;//相当与j=j-6
        System.out.println(j);
//        shellsort1(arr);
//        shellsort2(arr);
        shellsort3(arr);
    }

    //下面三个方法都是通过交换实现的
    //分成三次实现的
    public static void shellsort(int[] arr){
        int temp=0;
        //希尔排序的第一轮排序
        //因为第一轮排序，是将十个数分成了5组
        for(int i=5;i<arr.length;i++){
            for(int j=i-5;j>=0;j-=5){//j=j-5
                if(arr[j]>arr[j+5]){
                    temp=arr[j];
                    arr[j]=arr[j+5];
                    arr[j+5]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        for(int i=2;i<arr.length;i++){
            for(int j=i-2;j>=0;j-=2){//j=j-5
                if(arr[j]>arr[j+2]){
                    temp=arr[j];
                    arr[j]=arr[j+2];
                    arr[j+2]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0;j-=1){//j=j-5
                if(arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //通过一次实现
    public static void shellsort1(int[] arr){
        int length=arr.length;
        int temp=0;
        while(true){
            length=length/2;
            for(int i=length;i<arr.length;i++){
                for(int j=i-length;j>=0;j-=length){//j=j-5
                    if(arr[j]>arr[j+length]){
                        temp=arr[j];
                        arr[j]=arr[j+length];
                        arr[j+length]=temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
            if(length==1){
                break;
            }
        }
    }

    //老师代码实现
    public static void shellsort2(int[] arr){
        int temp=0;
        int count=0;
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                for(int j=i-gap;j>=0;j-=gap){//j=j-5
                    if(arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    //对希尔排序进行优化，并不是进行交换，而是按照之前的插入排序的移动方法进行的
    public static void shellsort3(int[] arr){
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                int j=i;
                int temp=arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap >=0 && temp<arr[j-gap]){
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    arr[j]=temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
