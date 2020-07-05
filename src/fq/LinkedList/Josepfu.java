package fq.LinkedList;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList cl=new CircleSingleLinkedList();

        cl.addBoy(25);
        cl.showBoy();
        cl.count(1,2,5);
    }
}

class CircleSingleLinkedList{
    private Boy first=null;

    public void addBoy(int nums){
        //校验
        if(nums<1){
            System.out.println("不正确");
        }
        Boy curBoy=null;

        for(int i=1;i<=nums;i++){
            Boy boy=new Boy(i);
            if(i==1){
                first=boy;
                first.setNext(first);
                curBoy=first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    //遍历环形链表
    public void showBoy(){
        if(first==null){
            System.out.println("链表为空");
        }
        Boy temp=first;
        while(true){
            if(temp.getNext()!=first){
                System.out.printf("小孩的编号为%d \n",temp.getNo());
                temp=temp.getNext();
            }else{
                System.out.printf("小孩的编号为%d \n",temp.getNo());
                break;
            }


        }

    }

    //计算出圈的顺序
    public void count(int staNo,int countNum,int nums){
        if(first==null || staNo<1 || staNo>nums){
            System.out.println("有误，请重新输入");
            return;
        }

        Boy helper=first;
        while(true){
            if(helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }

        for(int i=0;i<staNo-1;i++){
            first=first.getNext();
            helper=helper.getNext();
        }

        while(true){
            if(helper==first){
                break;
            }
            for(int i=0;i<countNum-1;i++){
                first=first.getNext();
                helper=helper.getNext();
            }
            System.out.println(first.getNo()+"号小孩出圈");
            first= first.getNext();
            helper.setNext(first);
        }
        System.out.println(first.getNo()+"号小孩留在圈中");
    }
}
class Boy{
    private int no;
    private Boy next;

    public Boy(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
