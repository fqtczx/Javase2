package fq.serach;

import java.util.ArrayList;

public class BinarySerach {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,6,7,7,7,712,34,45,67,89,89,89,89,89,123,467,678,878};
        System.out.println(binSer(arr,0,arr.length-1,7));
        System.out.println(binSer2(arr,0,arr.length,89));
    }

    /**
     * @param arr：待查找的数组
     * @param left：起始左边索引
     * @param right：起始右边索引
     * @param val：待查找的值
     * @return：下标，找不到则返回-1
     */
    public static int binSer(int[] arr,int left,int right,int val){
        int mid=(left+right)/2;
        //说明已经找完整个数组了，但是还没有找到，就返回-1
        if(left>right){
            return -1;
        }
        //分别进行左右递归查找数组的下标
        if(arr[mid]==val){
            return mid;
        }else if(arr[mid]>val){
            return binSer(arr,left,mid-1,val);
        }else{
            return binSer(arr,mid+1,right,val);
        }
    }

    //当数组中有多个相同的值时，如何返回所有的下标
    public static ArrayList binSer2(int[] arr, int left, int right, int val){
        int mid=(left+right)/2;
        ArrayList<Integer> al=new ArrayList<>();

        //说明已经找完整个数组了，但是还没有找到，就返回-1
        if(left>right){
            return al;
        }
        //分别进行左右递归查找数组的下标
        if(arr[mid]==val){
            for(int i=mid-1;i>=0;i--){
                if(arr[i]==val){
                    al.add(i);
                }else{
                    break;
                }
            }
            al.add(mid);
            for(int i=mid+1;i<arr.length;i++){
                if(arr[i]==val){
                    al.add(i);
                }else{
                    break;
                }
            }
            return al;
        }else if(arr[mid]>val){
            return binSer2(arr,left,mid-1,val);
        }else{
            return binSer2(arr,mid+1,right,val);
        }
    }
}
