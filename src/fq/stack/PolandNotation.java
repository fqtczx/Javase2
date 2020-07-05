package fq.stack;

import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String exp="3 4 + 5 x 6 -";
        //4x5-8+60+8/2
        String exp2="4 5 x 8 - 60 + 8 2 / +";//首先对于一个后缀表达式而言
        List<String> expr=getList(exp2);//先将其转化为List,便于后期的扫描
        System.out.println(expr);
        System.out.println(cal(expr));

        String zexp="18+((2+30)x42)-558";
        System.out.println(toInfixExp(zexp));
        System.out.println(houzhuiExp(toInfixExp(zexp)));
        System.out.println(cal(houzhuiExp(toInfixExp(zexp))));
    }

    public static List<String> getList(String exp){
        String[] split=exp.split(" ");//将后缀表达式以空格分为String数组
        ArrayList<String> exps=new ArrayList<>();
        for(String s:split){
            exps.add(s);//将中缀表达式中的每个元素放入Arraylist中
        }
        return exps;
    }

    //计算后缀表达式的方法，这里接受的是后缀表达式转成的List
    public static int cal(List<String> ls){
        Stack<String> sta=new Stack<>();//其实是只存储数字而已
        for(String str:ls){
            if(str.matches("\\d+")){//利用正则表达式判断是否是数字
                sta.push(str);
            }else{//是操作符的话
                int num2=Integer.parseInt(sta.pop());
                int num1=Integer.parseInt(sta.pop());
                int res=0;
                //注意这里的运算顺序
                if(str.equals("+")){
                    res=num1+num2;
                }else if(str.equals("-")){
                    res=num1-num2;
                }else if(str.equals("x")){
                    res=num1*num2;
                }else if(str.equals("/")){
                    res=num1/num2;
                }
                //最后将结果(int转String的两种方法)压入栈
                sta.push(String.valueOf(res));
                //sta.push(res+"");
            }
        }
        return Integer.parseInt(sta.pop());
    }

    //将正常的表达式转为中缀表达式，即将其各个元素分开，返回的是各个元素组成的List
    public static List<String> toInfixExp(String zexp){
        ArrayList<String> infixExp=new ArrayList<>();
        String keep="";
        for(int i=0;i<zexp.length();i++){
            if(zexp.charAt(i) <48 || zexp.charAt(i) >57){
                //是操作符的话，直接加入List中即可
                infixExp.add(String.valueOf(zexp.charAt(i)));
            }else{
                //是数字的话，这里也涉及到了多位数的拼接
                if(i==zexp.length()-1){
                    infixExp.add(String.valueOf(zexp.charAt(i)));
                }else{
                    keep=keep+zexp.charAt(i);
                    //看后一位是否还是数字
                    while(zexp.charAt(i+1)>=48 && zexp.charAt(i+1) <=57){
                        i=i+1;
                        keep=keep+zexp.charAt(i);
                        //到最后一位时，直接退出
                        if(i==zexp.length()-1){
                            break;
                        }
                    }
                    infixExp.add(keep);
                    keep="";//清空拼接中间变量
                }
            }
        }
        return infixExp;
    }

    //将中缀表达式的List转为后缀表达是List
    public static List<String> houzhuiExp(List<String> ls){
        Stack<String> s1=new Stack<>();
        ArrayList<String> s2=new ArrayList<>();

        for(String str:ls){
            if(str.matches("\\d+")){//数字直接添加
                s2.add(str);
            }else if(str.equals("(")){//左括号也直接添加
                s1.push(str);
            }else if(str.equals(")")){//右括号时，弹出并添加到s2中，直至遇到左括号
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//左括号也要弹出
            }else{
                //下面是核心代码
                //其他运算符时，直至遇到优先级不比其低时，再将其压入s1中
                while(s1.size() !=0 && Operation.getValue(s1.peek()) >= Operation.getValue(str)){
                    s2.add(s1.pop());
                }
                s1.push(str);
            }
        }

        //将s1中剩余的添加到s2中
        while(s1.size() !=0){
            s2.add(s1.pop());
        }
        return s2;
    }
}

//得到运算符的优先级的类
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=1;
    private static int DIV=1;

    public static int getValue(String operation){
        int result=0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "x":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                break;
        }
        return result;
    }
}