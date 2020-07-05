package fq.LinkedList;
import java.util.Stack;

public class SingleLinkedListDemo{
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode h2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode h3 = new HeroNode(3, "吴用", "智多星");
        HeroNode h4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList sl = new SingleLinkedList();
        //第一种添加
//        sl.add(h1);
//		sl.add(h2);
//	    sl.add(h3);
//		sl.add(h4);
//		sl.list();

        //第二种添加
        sl.addOr(h4);
        sl.addOr(h1);
        sl.addOr(h2);
        sl.addOr(h3);
        sl.list();
        System.out.println("倒数第二个节点为：" + find(sl.getHead(), 2));

        System.out.println("反转的结果如下");
        reverse(sl.getHead());
        sl.list();
        HeroNode h2u = new HeroNode(2, "小卢俊义", "大玉麒麟");
        sl.update(h2u);
        System.out.println("修改后的结果————————————————————");
        sl.list();
        System.out.println(getLength(sl.getHead()));

        //删除二号节点
        sl.delete(2);
        sl.delete(4);
        System.out.println("删除后的结果为————————————————");
        sl.list();
    }

    //获取到单链表有效节点的个数
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }

        int length = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找倒数第k个节点
    public static HeroNode find(HeroNode head, int k) {
        if (head.next == null) {
            return null;
        }
        int length = getLength(head);
        HeroNode temp = head.next;
        for (int i = 0; i < length - k; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //单链表的反转
    public static void reverse(HeroNode head){
        HeroNode revhead=new HeroNode(-1,"","");
        HeroNode cur=head.next;
        HeroNode next=null;
        while(cur!=null){
            next=cur.next;
            cur.next=revhead.next;
            revhead.next=cur;
            cur=next;
        }
        head.next=revhead.next;
    }

    //反序打印单链表，利用栈压入，先入后出
    //使用方式二(压入栈:先进后出)逆序打印链表
    //方式一，先将链表反转，再打印，但是这样破坏了链表的原有结构
    public static void rePrint(HeroNode head){
        if(head.next==null){
            return;
        }

        Stack<HeroNode> sh=new Stack<HeroNode>();
        HeroNode cur=head.next;
        while(cur!=null){
            sh.add(cur);
            cur=cur.next;
        }

        while(sh.size()>0){
            System.out.println(sh.pop());
        }
    }
}
class SingleLinkedList{
    private HeroNode head=new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //第一种添加：直接添加
    public void add(HeroNode hn){
        HeroNode temp= getHead();
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=hn;//这里每回添加都要遍历效率有点低
    }

    //第二种添加：按照排名顺序添加
    public void addOr(HeroNode hn){
        HeroNode temp= getHead();
        while(temp.next!=null){
            if(temp.no<hn.no && temp.next.no>hn.no){
                hn.next=temp.next;
                temp.next=hn;
                return;
            }
            temp=temp.next;
        }
        temp.next=hn;
    }

    //第二种添加的改进代码
    public void addByOr(HeroNode hn){
        //因为头部指针不能动，所以仍然用辅助指针来帮助找到添加的位置
        //因为是单链表，因此找的temp是位于添加位置的前一个节点
        HeroNode temp= getHead();
        boolean flag=false;//用来标识添加的编号是否存在

        while(true){
            if(temp.next==null){
                break;
            }

            if(temp.next.no>hn.no){//位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no==hn.no){
                flag=true;//说明编号存在
                break;
            }

            temp=temp.next;//后移，遍历当前列表
        }

        if(flag){
            System.out.printf("插入的英雄编号%d已经存在了,不能添加\n", hn.no);
        }else{
            //注意这里第一次添加的情况
            hn.next=temp.next;
            temp.next=hn;
        }
    }

    //单链表的修改节点的信息，根据no来修改
    public void update(HeroNode hn){
        if(getHead().next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp= getHead();
        while(temp.next!=null){
            if(temp.next.no==hn.no){
                temp.next.name=hn.name;
                temp.next.nickname= hn.nickname;
            }
            temp=temp.next;
        }
    }

    //修改方法改代码
    //修改节点的信息，根据no来修改，所以no不能变
    //1.根据new hn的no来修改
    public void update2(HeroNode newhn){
        if(getHead().next==null){
            System.out.println("链表为空");
            return;
        }

        //根据no找到需要修改的节点
        HeroNode temp= getHead().next;//这个建立在链表不为空的情况
        boolean flag=false;//表示是否找到了该节点
        while(true){
            if(temp==null){
                break;//已经遍历完链表了
            }

            if(temp.no==newhn.no){
                //找到了
                flag=true;
                break;
            }

            temp=temp.next;
        }

        if(flag){
            temp.name=newhn.name;
            temp.nickname=newhn.nickname;
        }else{//没有找到
            System.out.printf("没有找到编号为%d的节点，不能修改", newhn.no);
        }

    }

    //删除节点
    public void delete(int no){
        HeroNode temp= getHead();
        while(temp.next!=null){
            if(temp.next.no==no){
                temp.next=temp.next.next;
                return;
            }
            temp=temp.next;
        }
    }

    //删除的改进
    //思路：需要找到要删除节点的前一个节点，将其指向后一个节点即可
    public void del(int no){
        HeroNode temp= getHead();
        boolean flag=false;//表示是否找到待删除的节点
        while(true){
            if(temp.next==null){
                break;//已经到链表的最后了
            }

            if(temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }

        if(flag){
            temp.next=temp.next.next;
        }else{
            System.out.printf("要删除的%d节点不存在，无法删除\n", no);
        }

    }

    public void list(){
        if(getHead().next==null){
            return;
        }
        HeroNode temp= getHead();
        while(temp.next!=null){
            System.out.println(temp.next.toString());
            temp=temp.next;
        }
    }

}
class HeroNode{
    public  int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    public String toString(){
        return "HeroNode [no="+no+",name="+name+",nickname="+nickname+"]";
    }
}
