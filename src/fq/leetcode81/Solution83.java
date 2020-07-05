package fq.leetcode81;

import java.util.HashSet;

public class Solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        HashSet<Integer> set=new HashSet<Integer>();
        if(head==null){
            return head;
        }

        if(head.next ==null){
            return head;
        }

        ListNode helper=head;
        ListNode temp=head.next;
        set.add(head.val);

        while(temp !=null){
            if(set.add(temp.val)){
                temp=temp.next;
                helper=helper.next;
            }else if(!set.add(temp.val) && temp.next != null){
                temp=temp.next;
                helper.next=temp;
            }else if(!set.add(temp.val) && temp.next ==null){
                temp=null;
                helper.next=null;
            }
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
