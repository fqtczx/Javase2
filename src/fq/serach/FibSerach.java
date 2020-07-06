package fq.serach;

import java.util.Arrays;

public class FibSerach {
    public static int maxsize=20;
    public static void main(String[] args) {
        int[] arr={1,2,3,4,6,7,12,34,45,67};
        System.out.println(fibser(arr,67));
    }

    public static int[] fib(){
        int[] f=new int[maxsize];
        f[0]=1;
        f[1]=1;
        for(int i=2;i<maxsize;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }

    public static int fibser(int[] arr,int val){
        int low=0;
        int high=arr.length-1;
        int[] f=fib();
        int k=0;
        while(arr.length>f[k]-1){
            k++;
        }

        int[] temp=new int[f[k]];
        for(int i=0;i<arr.length;i++){
            temp[i]=arr[i];
        }
        for(int i=arr.length;i<f[k];i++){
            temp[i]=arr[arr.length-1];
        }

        while(low<=high){
            int mid=low+f[k-1]-1;
            if(temp[mid]>val){
                high=mid-1;
                k--;
            }else if(temp[mid]<val){
                low=mid+1;
                k-=2;
            }else{
                if(mid>high){
                    return high;
                }else{
                    return mid;
                }
            }
        }
        return -1;
    }
}
