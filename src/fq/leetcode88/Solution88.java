package fq.leetcode88;

import java.util.Arrays;

public class Solution88 {
    public static void main(String args[]){
        int[] nums1={1,2,3,0,0,0};
        int m=3;
        int[] nums2={2,5,6};
        int n=3;
        merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums3=new int[m+n];
        for(int i=0;i<m+n;i++){
            if(i<m){
                nums3[i]=nums1[i];
            }else {
                nums3[i] = nums2[i - m];
            }
        }
        Arrays.sort(nums3);

        for(int i=0;i<m+n;i++){
            nums1[i]=nums3[i];
        }
    }

    //改进算法
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        for(int i=m;i<m+n;i++){
                nums1[i] = nums2[i - m];
        }
        Arrays.sort(nums1);
    }

    //官方双指针算法
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int [] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

        // if there are still elements to add
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }

    //官方三指针算法
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        //两个指针分别放在两个有序数组的结尾
        int p1 = m - 1;
        int p2 = n - 1;
        //另一个指针放在有序数组nums1的结尾
        int p = m + n - 1;

        while ((p1 >= 0) && (p2 >= 0)){//这说明还有数没有比较完
            //分别取出两个数组中的数进行比较，然后将较大的放进nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        //最后将nums2中剩的数拷贝进nums1中
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
