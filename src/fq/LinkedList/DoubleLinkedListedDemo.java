package fq.LinkedList;

public class DoubleLinkedListedDemo {
    public static void main(String args[]){
        HeroNode2 h1=new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 h2=new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 h3=new HeroNode2(3, "吴用", "智多星");
        HeroNode2 h4=new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList dl=new DoubleLinkedList();

//      dl.add(h1);
//		dl.add(h2);
//	    dl.add(h3);
//		dl.add(h4);

//        dl.addOr(h1);
//        dl.addOr(h3);
//        dl.addOr(h4);
//        dl.addOr(h2);

        dl.add(h1);
        dl.add(h3);
        dl.add(h4);
        dl.add(h2);

		dl.list();

        // 修改英雄信息测试
        HeroNode2 newhn = new HeroNode2(4, "公孙胜", "入云龙");
        dl.update(newhn);
        System.out.println("修改后的英雄信息");
        dl.list();

        // 删除节点
        dl.delete(3);
        System.out.println("删除后的链表为：");
        dl.list();
    }
}

class DoubleLinkedList{
    private HeroNode2 head=new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    public void list(){
        if(getHead().next==null){
            return;
        }
        HeroNode2 temp= getHead();
        while(temp.next!=null){
            System.out.println(temp.next.toString());
            temp=temp.next;
        }
    }

    //添加方法，默认添加到队尾
    public void add(HeroNode2 hn){
        HeroNode2 temp= getHead();
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=hn;//这里每回添加都要遍历效率有点低
        hn.pre=temp;
    }

    //第二种添加：按照排名顺序添加
    public void addOr(HeroNode2 hn){
        HeroNode2 temp= getHead();
        while(temp.next!=null){
            if(temp.no<hn.no && temp.next.no>hn.no){
                hn.next=temp.next;
                temp.next=hn;
                hn.pre=temp;
                hn.next.pre=hn;
                return;
            }
            temp=temp.next;
        }
        temp.next=hn;
    }

    //单链表的修改节点的信息，根据no来修改
    public void update(HeroNode2 hn){
        if(getHead().next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp= getHead();
        while(temp.next!=null){
            if(temp.next.no==hn.no){
                temp.next.name=hn.name;
                temp.next.nickname= hn.nickname;
            }
            temp=temp.next;
        }
    }

    //删除方法
    public void delete(int no){
        HeroNode2 temp= getHead();
        while(temp.next!=null){
            if(temp.no==no){
                temp.pre.next=temp.next;
                //这里我们的代码有问题，如果是最后一个节点的话，就不需要执行下面这句话，否则会出现空指针异常
                if(temp.next!=null){
                    temp.next.pre=temp.pre;
                }
                return;
            }
            temp=temp.next;
        }
    }
}

class HeroNode2{
    public  int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;

    public HeroNode2(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    public String toString(){
        return "HeroNode [no="+no+",name="+name+",nickname="+nickname+"]";
    }
}