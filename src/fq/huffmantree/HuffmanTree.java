package fq.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node root=huffmanTree(arr);
        root.preOrder();
    }

    //创建赫夫曼树的方法
    public static Node huffmanTree(int[] arr){
        ArrayList<Node> al=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            al.add(new Node(arr[i]));
        }
        Collections.sort(al);//这里实现了接口

//        Node left=al.get(0);
//        Node right=al.get(1);
//        Node root=new Node(left.val+right.val);
//        root.left=left;
//        root.right=right;
//        al.remove(left);
//        al.remove(right);
//        al.add(root);
//        Collections.sort(al);
//        System.out.println(al);
        //上述代码可用循环解决
        while(al.size()!=1){
            Node left=al.get(0);
            Node right=al.get(1);
            Node root=new Node(left.val+right.val);
            root.left=left;
            root.right=right;
            al.remove(left);
            al.remove(right);
            al.add(root);
            Collections.sort(al);
        }
        System.out.println(al);
        return al.get(0);
    }
}

class Node implements Comparable<Node>{
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        return this.val-o.val;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
}