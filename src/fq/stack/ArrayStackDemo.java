package fq.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack as=new ArrayStack(4);
        as.push(10);
        as.push(20);
        as.push(30);
        as.push(40);

        as.list();
    }
}

class ArrayStack{
    private int maxsize;
    private int[] stack;
    private int top=-1;

    public ArrayStack(int maxsize){
        this.maxsize=maxsize;
        stack=new int[maxsize];
    }

    public boolean isFull(){
        return top==maxsize-1;
    }
    public boolean isEmpty(){
        return top==-1;
    }

    public void push(int i){
        if(isFull()){
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top]=i;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        int value=stack[top];
        top--;
        return value;
    }

    public void list(){
        if(isEmpty()){
            System.out.println("栈已空，没有数据");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
