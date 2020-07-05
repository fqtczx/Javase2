package fq.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr={-2,3,34,45,78,6,135,32,-43,43};
        quicksort(arr,0,arr.length-1);
    }

    //快速排序的方法，传入要排序的数组，以及开始时左右指针所处的位置
    public static void quicksort(int[] arr,int left,int right){
        int l=left;
        int r=right;//记录左右指针的位置，后面要进行操作
        int mid=arr[(left+right)/2];//得到处在数组中间位置的数值，以其为界
        int temp=0;//用于交换的中间变量

        while (l<r){//两者并未相遇，说明没有遍历完
            //从左边开始，向右移动，直到找到大于等于中间值的一个数，并记录其下标
            //注意这里等于很重要，说明l最多只能移动到mid所在的位置，就会停下来
            while(arr[l]<mid){
                l+=1;
            }
            //从右边开始，向左移动，直到找到比中间值小的一个数，并记录其下标
            while(arr[r]>mid){
                r-=1;
            }

            //两者相遇，结束循环
            if(l>=r){
                break;
            }

            //交换两者
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;

            //下面这两种情况说明一边找到了，而一边没有找到，就会将mid与另一个找到的进行交换
            if(arr[l]==mid){
                r--;
            }
            if(arr[r]==mid){
                l++;
            }
        }
        System.out.println(Arrays.toString(arr));
        //这里要注意，当退出循环时两个指针指向同一个位置时，要将两者错开
        //没有的话会发生栈溢出
//        if(l==r){
        l++;
        r--;
//        }

        //继续分成两半，进行递归循环排序，注意这里的if判断条件
        if(left<r){
            quicksort(arr,left,r);
        }
        if(right>l){
            quicksort(arr,l,right);
        }
    }
}
