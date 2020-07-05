package fq.recursion;

public class Queue8 {
    int max=8;
    int[] array=new int[max];
    static int count=0;

    public static void main(String[] args) {
        Queue8 q=new Queue8();
        q.check(0);
        System.out.println(count);
    }

    public void print(){
        count++;
        for(int i=0;i<array.length;i++){
           System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public boolean judge(int n){
        for (int i=0;i<n;i++){
            if(array[i]==array[n] ||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    public void check(int n){
        if(n==8){
            print();
            return;
        }
        for (int i=0;i<max;i++){
            array[n]=i;
            if(judge(n)){
                check(n+1);
            }
        }
    }
}
