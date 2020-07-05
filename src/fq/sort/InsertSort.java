package fq.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr={-2,3,34,45,78,135,32,-43,43,-3};
        insertsort(arr);

        //排序完成后，进行数组的打印
        System.out.println(Arrays.toString(arr));

        int[] arr2={-2,3,34,45,78,135,32,-43,43,-3};
        insertsort2(arr2);
    }

    public static void insertsort(int[] arr){
        to:for(int i=1;i<arr.length;i++){
            int insertval=arr[i];
            for(int j=i-1;j>=0;j--){
                if(insertval<arr[j]){
                    arr[j+1]=arr[j];
                    if(j==0){//注意到最后一次的结果
                        arr[j]=insertval;
                    }
                }else{
                    arr[j+1]=insertval;
                    continue to;
                }
            }
        }
    }

    public static void insertsort2(int[] arr){
        for(int i=1;i<arr.length;i++){
            int insertval=arr[i];//待插入的数值
            int insertindex=i-1;//从待插入的位置前一位进行遍历

            /*
            给insertval找到插入的位置
            1.insertindex>=0保证索引不越界
            2.insertval<arr[insertindex]说明待插入的数还没有找到插入的位置
            3.就需要将arr[insertindex]后移，注意这里的具体实现
             */
            while(insertindex>=0 && insertval<arr[insertindex]){//满足索引不越界，且没有找到待插入的位置
                arr[insertindex+1]=arr[insertindex];//后移一位
                insertindex--;
            }

            //说明找到了待插入的位置
            arr[insertindex+1]=insertval;//注意这里的加1操作
            System.out.printf("第%d轮的结果为:",i);
            System.out.println(Arrays.toString(arr));
        }
    }
}
