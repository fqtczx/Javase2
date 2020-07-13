package fq.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable ht=new HashTable(7);//初始化，共有7条链表
        String key="";
        Scanner sc=new Scanner(System.in);
        System.out.println();
        while(true){
            System.out.println("add:添加");
            System.out.println("list:列表");
            System.out.println("find:查找");
            System.out.println("add:退出");
            key=sc.next();//读取键盘字符
            switch (key){
                case "add":
                    System.out.println("请输入id");
                    int id=sc.nextInt();
                    System.out.println("请输入姓名");
                    String name=sc.next();
                    ht.add(new Emp(id,name));
                    break;//注意这里的break
                case "list":
                    ht.list();
                    break;
                case "find":
                    System.out.println("请输入id");
                    int findid=sc.nextInt();
                    ht.find(findid);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTable{
    //其底层是存储EmpLinkedList的数组
    private EmpLinkedList[] empls;
    int size;//给定数组的大小

    //构造方法，这里传入了大小的参数
    public HashTable(int size){
        this.size=size;
        empls=new EmpLinkedList[size];//这里要为每一个EmpLinkedList初始化，不然
        //会造成空指针异常，遍历初始化
        for(int i=0;i<empls.length;i++){
            empls[i]=new EmpLinkedList();
        }
    }

    //添加方法，这里用到了散列函数，通过id决定将雇员放在那一条链表中
    public void add(Emp emp){
        empls[hashFun(emp.id)].add(emp);
    }

    //散列函数，通过取模决定
    public int hashFun(int id){
        return id%size;
    }

    //遍历方法，取出每条链表，通过链表的list()来实现功能
    public void list(){
        for(int i=0;i<size;i++){
            empls[i].list(i);
        }
    }

    //查找方法，也是通过链表的方法实现的
    public void find(int id){
        Emp findemp=empls[hashFun(id)].find(id);
        if(findemp!=null){
            System.out.print("在第"+(hashFun(id)+1)+"条链表中找到该雇员");
            System.out.print(findemp.toString());
        }else{
            System.out.println("没有找到该雇员");
        }

    }
}

//此类通过链表的方式存储id符合某些条件的雇员
class EmpLinkedList{
    private Emp head;//头节点，默认为空

    //添加雇员的方法，考虑头节点为空的情况
    //假定，当添加雇员时，id是自增长的，即id的分配总是从小到大
    //因此我们将雇员直接加到本链表的最后即可
    public void add(Emp emp){
        if(head==null){
            head=emp;
        }else{
            Emp temp=head;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=emp;
        }
    }

    //打印此条链表中含有的雇员信息
    //这里传入了第几条EmpLinkedList的参数
    public void list(int no){
        if(head==null){
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        System.out.print("第"+(no+1)+"条链表信息为：");
        Emp temp=head;
        System.out.println(temp.id+" "+temp.name);
        while(temp.next!=null){
            System.out.println(temp.next.id+" "+temp.next.name);
            temp=temp.next;
        }
    }

    //通过id来查找雇员信息，遍历查找
    public Emp find(int id){
        if(head==null){
            System.out.println("链表为空");
            return null;
        }
        Emp temp=head;
        while(temp!=null){
            if(temp.id==id){
                return temp;
            }
            temp=temp.next;
        }
        return null;
    }
}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;

    //构造方法
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //重写toString()方法
    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}