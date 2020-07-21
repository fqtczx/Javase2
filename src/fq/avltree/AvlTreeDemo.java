package fq.avltree;

public class AvlTreeDemo {
    public static void main(String[] args) {
//        int[] arr={4,3,6,5,7,8};
        int[] arr={10,12,8,9,7,6};
        AvlTree at=new AvlTree();
        for(int i=0;i<arr.length;i++){
            at.add(new Node(arr[i]));
        }
        at.infixOrder();
        System.out.println("在没有旋转处理之前");
        System.out.println("树的高度是："+at.getRoot().height());
        System.out.println("左子树的高度是："+at.getRoot().leftHeight());
        System.out.println("右子树树的高度是："+at.getRoot().rightHeight());
        System.out.println("根节点是："+at.getRoot());//处理完之后，根节点的值就变为6了
    }
}

class AvlTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node n){
        if(root==null){
            root=n;
        }else{
            root.add(n);
        }
    }

    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }
    }

    public Node search(int val){
        if(root==null){
            return null;
        }else{
            return root.search(val);
        }
    }

    public Node searchPar(int val){
        if(root==null){
            return null;
        }else{
            return root.searchPar(val);
        }
    }

    public void del(int val){
        if(root==null){
            return;
        }

        Node target=search(val);
        if(target==null){
            return;
        }
        Node par=searchPar(val);
//        if(par==null){//这里代码是有问题的
//            root=null;
//            return;
//        }

        if(target.left==null && target.right==null){//删除的是叶子节点的情况
            if( par.left!=null && par.left.val==val ){
                par.left=null;
            }else{
                par.right=null;
            }
        }else if(target.left!=null && target.right!=null){//先将有两颗子树的节点情况排除出去
            int value=delRightMin(target.right);
            target.val=value;
        }else{//只有一颗子树的情况
            if(target.left!=null){
                if(par!=null){
                    if(par.left.val==val){
                        par.left=target.left;
                    }else{
                        par.right=target.left;
                    }
                }else{
                    root=target.left;
                }
            }else{
                if(par!=null){
                    if(par.left.val==val){
                        par.left=target.right;
                    }else{
                        par.right=target.right;
                    }
                }else{
                    root=target.right;
                }
            }
        }
    }

    //编写一个方法，在左右都有子树的情况下，寻找右子树最小的节点值
    //并将该值返回，并删除该节点
    public int delRightMin(Node n){
        Node temp=n;
        while(temp.left != null){
            temp=temp.left;
        }
        del(temp.val);
        return temp.val;
    }
}

class Node{
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

    //添加节点的方法
    //使用递归的方法添加节点，需要满足二叉排序树的要求
    public void add(Node n){
        if(n.val<=val){
            if(this.left==null){
                this.left=n;
                return;
            }else{
                this.left.add(n);//这里使用了左递归进行添加
            }
        }

        if(n.val>val){
            if(this.right==null){
                this.right=n;
                return;
            }else{
                this.right.add(n);
            }
        }

        //当添加完一个节点后，当右子树的高度-左子树的高度>1,就要进行左旋转
        if(rightHeight()-leftHeight()>1){
            if(right !=null && right.leftHeight()>right.rightHeight()){
                right.rightrotate();
            }
            leftrotate();
            return;//注意这里是必须要的
        }

        //当添加完一个节点后，当左子树的高度-右子树的高度>1,就要进行右旋转
        if(leftHeight()-rightHeight()>1){
            if(left !=null && left.rightHeight()>left.leftHeight()){
                left.leftrotate();
            }
            rightrotate();
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    //返回以该节点为根节点的树的高度
    public int height(){
        return Math.max(left==null? 0:left.height(),right==null? 0:right.height())+1;
    }

    //返回左子树的高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }else{
            return left.height();
        }
    }

    //返回右子树的高度
    public int rightHeight(){
        if(right==null){
            return 0;
        }else{
            return right.height();
        }
    }

    //左旋转的方法
    public void leftrotate(){
        Node n=new Node(val);
        n.left=left;
        n.right=right.left;
        val=right.val;
        right=right.right;
        left=n;
    }

    //右旋转的方法
    public void rightrotate(){
        Node n=new Node(val);
        n.right=right;
        n.left=left.right;
        val=left.val;
        left=left.left;
        right=n;
    }

    /**
     *
     * @param val 待查找节点的值
     * @return 查找到的节点值
     */
    //使用递归查找待查找的节点，并将其返回
    public Node search(int val){
        if(this.val==val){
            return this;
        }else if(this.val<val){
            if(this.right==null){
                return null;
            }else{
                return this.right.search(val);
            }
        }else{
            if(this.left==null){
                return null;
            }else{
                return this.left.search(val);
            }
        }
    }

    //返回传入节点的父节点
    public Node searchPar(int val){
        if((this.left!=null && this.left.val==val) ||(this.right!=null && this.right.val==val)){
            return this;
        }else{
            if(val<=this.val && this.left!=null){
                return this.left.searchPar(val);
            }else if(val>this.val && this.right!=null){
                return this.right.searchPar(val);
            }else{
                return null;
            }
        }
    }
}
