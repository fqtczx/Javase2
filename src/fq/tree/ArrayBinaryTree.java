package fq.tree;

public class ArrayBinaryTree {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        preOrder(arr,0);
        System.out.println();
        infixOrder(arr,0);
        System.out.println();
        postOrder(arr,0);
    }

    public static void preOrder(int[] arr,int index){
        if(arr ==null && arr.length==0){
            System.out.println("数组为空，无法遍历");
        }
        System.out.print(arr[index]+" ");

        if((2*index+1)<arr.length){
            preOrder(arr,2*index+1);
        }

        if((2*index+2)<arr.length){
            preOrder(arr,2*index+2);
        }
    }

    public static void infixOrder(int[] arr,int index){
        if(arr ==null && arr.length==0){
            System.out.println("数组为空，无法遍历");
        }

        if((2*index+1)<arr.length){
            infixOrder(arr,2*index+1);
        }

        System.out.print(arr[index]+" ");

        if((2*index+2)<arr.length){
            infixOrder(arr,2*index+2);
        }
    }

    public static void postOrder(int[] arr,int index){
        if(arr ==null && arr.length==0){
            System.out.println("数组为空，无法遍历");
        }
        if((2*index+1)<arr.length){
            postOrder(arr,2*index+1);
        }
        if((2*index+2)<arr.length){
            postOrder(arr,2*index+2);
        }
        System.out.print(arr[index]+" ");
    }
}
