package fq.serach;

public class InsertSerach {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,6,7,12,34,45,67,89,123,467,678,878};
        System.out.println(insertSer(arr,0,arr.length-1,878));
    }

    public static int insertSer(int[] arr,int left,int right,int val){
        int mid=left+(val-arr[left])*(right-left)/(arr[right]-arr[left]);
        //说明已经找完整个数组了，但是还没有找到，就返回-1
        //后面两个判断条件是必须的，否则容易越界
        if(left>right || val>arr[arr.length-1] || val<arr[0]){
            return -1;
        }

        //分别进行左右递归查找数组的下标
        if(arr[mid]==val){
            return mid;
        }else if(arr[mid]>val){
            return insertSer(arr,left,mid-1,val);
        }else{
            return insertSer(arr,mid+1,right,val);
        }
    }
}
