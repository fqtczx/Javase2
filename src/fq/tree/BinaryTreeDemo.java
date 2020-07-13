package fq.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree bt=new BinaryTree();

        //需要创建的节点
        HeroNode root=new HeroNode(1,"宋江");
        HeroNode h2=new HeroNode(2,"吴用");
        HeroNode h3=new HeroNode(3,"卢俊义");
        HeroNode h4=new HeroNode(4,"林冲");
        HeroNode h5=new HeroNode(5,"关胜");
        bt.setRoot(root);

        //先手动创建二叉树，之后通过递归的方式构建二叉树
        root.setLeft(h2);
        root.setRight(h3);
        h3.setRight(h4);
        h3.setLeft(h5);
        //二叉树的前序遍历
        System.out.println("二叉树的前序遍历结果为：");
        bt.preOrder();
        //二叉树的中序遍历
        System.out.println("二叉树的中序遍历结果为：");
        bt.infixOrder();
        //二叉树的后序遍历
        System.out.println("二叉树的后序遍历结果为：");
        bt.postOrder();

        //前中后序遍历查找
        System.out.println("前中后序遍历查找结果");
        System.out.println(bt.preFind(1).toString());
        System.out.println(bt.infixFind(2).toString());
        System.out.println(bt.postFind(2).toString());

    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode hn){
        root=hn;
    }

    //前序遍历
    public void preOrder(){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder(){
        if(root!=null){
            root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preFind(int no){
        if(root != null){
            return root.preFind(no);
        }else{
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixFind(int no){
        if(root != null){
            return root.infixFind(no);
        }else{
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postFind(int no){
        if(root != null){
            return root.postFind(no);
        }else{
            return null;
        }
    }
}

//构建节点信息
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//左子节点
    private HeroNode right;//右子节点

    //构造方法
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归实现前序遍历
    public void preOrder(){
        System.out.println(this.toString());
        if(left!=null){
            this.left.preOrder();
        }
        if(right!=null){
            this.right.preOrder();
        }
    }

    //递归实行中序遍历
    public void infixOrder(){
        if(left!=null){
            this.left.infixOrder();
        }
        System.out.println(this.toString());
        if(right!=null){
            this.right.infixOrder();
        }
    }

    //递归实现后序遍历
    public void postOrder(){
        if(left!=null){
            this.left.postOrder();
        }
        if(right!=null){
            this.right.postOrder();
        }
        System.out.println(this.toString());
    }

    //前序查找
    public HeroNode preFind(int no){
        System.out.print("q");
        if(this.no==no){
            return this;
        }

        //则判断当前节点的左节点是否为空，如果不为空，则递归前序查找
        //如果左递归前序查找找到，则返回
        HeroNode res=null;//这个变量很重要，用来记录找到的节点，并返回
        if(this.left!=null){
            res=this.left.preFind(no);
        }
        if(res!=null){
            return res;
        }

        //则判断当前节点的右节点是否为空，如果不为空，则递归前序查找
        //如果右递归前序查找找到，则返回
        if(this.right!=null){
            res=this.right.preFind(no);
        }
        return res;
    }

    //中序遍历查找
    public HeroNode infixFind(int no){
        HeroNode res=null;//这个变量很重要，用来记录找到的节点，并返回
        if(this.left!=null){
            res=this.left.infixFind(no);
        }

        if(res!=null){
            return res;
        }

        System.out.print("z");
        if(this.no==no){
            return this;
        }

        if(this.right!=null){
            res=this.right.infixFind(no);
        }
        return res;
    }

    //后序遍历查找
    public HeroNode postFind(int no){
        HeroNode res=null;//这个变量很重要，用来记录找到的节点，并返回
        if(this.left!=null){
            res=this.left.postFind(no);
        }

        if(res!=null){
            return res;
        }

        if(this.right!=null){
            res=this.right.postFind(no);
        }

        if(res!=null){
            return res;
        }

        System.out.print("h");
        if(this.no==no){
            return this;
        }
        return res;
    }
}
