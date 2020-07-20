package fq.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,9,2};
        BinarySortTree bst=new BinarySortTree();
        for(int i=0;i<arr.length;i++){
            bst.add(new Node(arr[i]));
        }
        bst.infixOrder();
        System.out.println("删除叶子节点");
        bst.del(3);
        bst.infixOrder();
    }
}

class BinarySortTree{
    private Node root;

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

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    /**
     *
     * @param val 待查找节点的值
     * @return 查找到的节点值
     */
    //使用递归查找待查找的节点
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