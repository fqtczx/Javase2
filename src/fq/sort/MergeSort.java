package fq.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr1={1,5,8,11,12};
        int[] arr2={-3,-2,0,9,10,15};
        System.out.println(Arrays.toString(merge(arr1,arr2)));

        int[] arr={-2,3,34,45,78,6,135,32};
        int[] temp=new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(temp));
        System.out.println(Arrays.toString(arr));
    }

    //写个方法，将两个有序数组合并为一个有序数组
    public static int[] merge(int[] arr1,int[] arr2){
        int[] temp=new int[arr1.length+arr2.length];//创建一个新的数组，长度为两个数组的长度之和
        int index=0;

        for(int i=0,j=0;i<arr1.length && j<arr2.length;){//两个指针分别对两个数组进行遍历
            if(arr1[i]<arr2[j]){//将小的那个放到新的数组中
                temp[index]=arr1[i];
                index++;
                i++;
            }else{
                temp[index]=arr2[j];
                index++;
                j++;
            }
            //当其中一个数组全部处理完后，注意这里的index指针其实指向了下一个空的位置
            //并且i,j也指向了后一个位置
            if(i==arr1.length){
                for(int k=j;k<arr2.length;k++){
                    temp[index]=arr2[k];
                    index++;
                }
            }
            if(j==arr2.length){
                for(int k=i;k<arr1.length;k++){
                    temp[index]=arr1[k];
                    index++;
                }
            }
        }
        return temp;
    }

    //老师写的方法

    /**
     *
     * @param arr:看作一个数组
     * @param left:最左边的索引
     * @param mid:中间索引,即第二个开始的前一个位置
     * @param right:最右边的索引
     * @param temp:中转的数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        System.out.println(left+"xx"+mid+"xx"+right);
        int i=left;//左边有序序列的初始索引
        int j=mid+1;//右边有序序列的初始索引
        //i和j相当于两个移动的指针
        int index=0;//中转数组索引

        while(i<=mid && j<=right){//两个指针分别对两个数组进行遍历
            if(arr[i]<arr[j]){//将小的那个放到新的数组中
                temp[index]=arr[i];
                index++;
                i++;
            }else{
                temp[index]=arr[j];
                index++;
                j++;
            }//当其中一个数组全部处理完后，注意这里的index指针其实指向了下一个空的位置
            //并且i,j也指向了后一个位置
        }

        //将未全部处理的那个数组的数据全部填充到temp中去
        while(i<=mid){
            temp[index]=arr[i];
            index++;
            i++;
        }
        while(j<=right){
            temp[index]=arr[j];
            index++;
            j++;
        }//因为这里是移动后进行了++操作，所以注意这里的<=的运用

        //下面这一步非常重要，将temp数组中的元素再拷贝回原始数组，进行覆盖
        //注意不是每次都拷贝所有
        //第一次合并left=0,right=1
        //第二次合并left=2,right=3
        //第三次合并left=0,right=3
        index=0;
        int tempLeft=left;
        System.out.println(left+" "+right);
        while(tempLeft<=right){//这里也是<=
            arr[tempLeft]=temp[index];
            index++;
            tempLeft++;
        }
    }

    //注意这里的所有方法用的都是temp这个公用数组
    //分+和的方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid=(left+right)/2;//得到中间的索引
            mergeSort(arr,left,mid,temp);//向左递归进行分解
            mergeSort(arr,mid+1,right,temp);//向右进行递归分解
            merge(arr,left,mid,right,temp);//栈的机制进行合并
        }
    }
}
