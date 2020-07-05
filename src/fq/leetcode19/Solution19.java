package fq.leetcode19;

public class Solution19 {
    public static void main(String args[]){

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp=head;
        int length=1;

        while(temp.next!=null){
            length++;
            temp=temp.next;
        }

        temp=head;

        for(int i=0;i<length;i++){

            if(length==n){
                head=head.next;
                break;
            }
            if(i==length-n-1){
                temp.next=temp.next.next;
                break;
            }
            temp=temp.next;
        }

        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;

        //先移动第一个指针n+1
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        //在同时移动两个指针，保证两者之间的间距
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        //第二个指针指向的就是要删除节点的前一个节点
        second.next = second.next.next;
        return head;
    }

    //当删除只有一个节点时，上述代码会出现问题，因而引入一个哑节点，连接至头节点的前面，解决上述问题
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0);//辅助节点的引入，也就是哑节点
        dummy.next = head;

        ListNode first = dummy;
        ListNode second = dummy;

        //先移动第一个指针，使两者之间有着n的间距
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        //在同时移动两个指针，保证两者之间的间距
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        //找到前驱节点，并删除
        second.next = second.next.next;

        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }
}
