package hashtable;

public class HashTableDemo {
    public static void main(String[] args) {

    }
}

class HashTable{
    private EmpLinkedList[] empls;
    int size;

    public HashTable(int size){
        this.size=size;
        empls=new EmpLinkedList[size];
    }

    public void add(Emp emp){
        empls[hashFun(emp.id)].add(emp);
    }

    public int hashFun(int id){
        return id%size;
    }

    public void list(){
        for(EmpLinkedList elinklist:empls){
            elinklist.list();
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

    public void list(){
        if(head==null){
            System.out.println("当前链表为空");
            return;
        }
        System.out.println("当前链表信息为：");
        Emp temp=head;
        while(temp.next!=null){
            System.out.println(temp.id+" "+temp.name);
            temp=temp.next;
        }
        System.out.println();
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
}
