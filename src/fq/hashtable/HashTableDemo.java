package fq.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable ht=new HashTable(7);
        String key="";
        Scanner sc=new Scanner(System.in);
        System.out.println();
        while(true){
            System.out.println("add:添加");
            System.out.println("list:列表");
            System.out.println("find:查找");
            System.out.println("add:退出");
            key=sc.next();
            switch (key){
                case "add":
                    System.out.println("请输入id");
                    int id=sc.nextInt();
                    System.out.println("请输入姓名");
                    String name=sc.next();
                    ht.add(new Emp(id,name));
                    break;
                case "list":
                    ht.list();
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                case "find":
                    System.out.println("请输入id");
                    int findid=sc.nextInt();
                    ht.find(findid);
                    break;
                default:
                    break;
            }
        }
    }
}

class HashTable{
    private EmpLinkedList[] empls;
    int size;

    public HashTable(int size){
        this.size=size;
        empls=new EmpLinkedList[size];
        for(int i=0;i<empls.length;i++){
            empls[i]=new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        empls[hashFun(emp.id)].add(emp);
    }

    public int hashFun(int id){
        return id%size;
    }

    public void list(){
        for(int i=0;i<size;i++){
            empls[i].list(i);
        }
    }

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

class EmpLinkedList{
    private Emp head;

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

class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}