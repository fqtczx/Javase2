package fq.stack;

public class Calculator {
    public static void main(String[] args) {
        String exp="30+2*6-20";//需要进行计算的中缀表达式
        ArrayStack2 num=new ArrayStack2(10);//创建数栈
        ArrayStack2 opers=new ArrayStack2(20);//创建符号栈
        int index=0;//扫描指针
        int num1=0;
        int num2=0;
        int oper=0;//操作符
        int res=0;//运算的结果
        char ch=' ';//保存扫描得到的字符
        String keepnum="";//用于拼接多位数的中间变量

        while(true){
            ch=exp.charAt(index);//得到字符
            if(opers.isOper(ch)){//是操作符
                if(opers.isEmpty()){//操作符栈为空，直接压入
                    opers.push(ch);
                }else{//操作符栈不为空的话，将此操作符与栈顶操作符优先级进行比较
                    //优先级小于等于栈顶操作符的优先级
                    if(opers.priority(ch)<=opers.priority(opers.peek())){
                        //数栈弹出两个数，操作符栈弹出一个操作符
                        num1=num.pop();
                        num2=num.pop();
                        oper=opers.pop();
                        //利用数栈的方法进行计算结果
                        res=num.cal(num1,num2,oper);
                        //结果压入数栈
                        num.push(res);
                        //操作符也压入符号栈
                        opers.push(ch);
                    }else{
                        opers.push(ch);
                    }
                }
            }else{
                //第一版代码，有点问题
                //num.push(ch-48);//这里注意数字的ascii码

                //下面的代码是核心代码，处理多位数
                keepnum+=ch;//这里如果是数字的话，会一直进行拼接

                if(index==exp.length()-1){
                    //遍历到最后一位时，直接将拼接的字符压入数栈
                    num.push(Integer.parseInt(keepnum));
                }else{
                    if(opers.isOper(exp.charAt(index+1))){
                        //当下一位是操作符时，就将前面拼接的数字压入数栈，同时不要进行拼接了
                        num.push(Integer.parseInt(keepnum));
                        keepnum="";//注意这里要将这个清空，以便下次进行拼接
                    }
                }

            }
            index++;//下一个位置
            if(index >= exp.length()){//退出while的条件
                break;
            }
        }

        while(true){
            if(opers.isEmpty()){//结束的条件：操作符栈为空
                break;
            }else{
                num1=num.pop();
                num2=num.pop();
                oper=opers.pop();
                res=num.cal(num1,num2,oper);
                num.push(res);//依次压出进行相应的运算
            }
        }
        System.out.printf("表达式%s=%d",exp,num.pop());
    }
}

class ArrayStack2{
    private int maxsize;
    private int[] stack;
    private int top=-1;

    public ArrayStack2(int maxsize){
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

    //返回运算符的优先级，使用数值表示，数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;
        }
    }

    //判断扫描得到的字符是否是一个操作符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //将数栈弹出的两个数和符号栈弹出的运算符进行相应的运算，这里注意两个数的顺序
    public int cal(int num1,int num2,int oper){
        int res=0;
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }

    //返回栈顶的元素，但并不弹出
    public int peek(){
        return stack[top];
    }
}