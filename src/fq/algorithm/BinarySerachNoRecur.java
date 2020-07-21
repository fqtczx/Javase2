package fq.algorithm;

public class BinarySerachNoRecur {
    public static void main(String[] args) {
        int[] arr={-10,-5,-4,-2,0,1,2,5,6,10,13};
        for(int i=0;i<arr.length;i++){
            System.out.println(serach(arr,arr[i]));
        }

    }

    public static int serach(int[] arr, int val){
        int left=0;
        int right=arr.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(arr[mid]<val){
                left=mid+1;
            }else if(arr[mid]>val){
                right=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
