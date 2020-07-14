package fq.tree.threadbinarytree;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode h1=new HeroNode(1,"宋江");
        HeroNode h2=new HeroNode(3,"吴用");
        HeroNode h3=new HeroNode(6,"卢俊义");
        HeroNode h4=new HeroNode(8,"林冲");
        HeroNode h5=new HeroNode(10,"关胜");
        HeroNode h6=new HeroNode(14,"高俅");

        ThreadBinaryTree tbt=new ThreadBinaryTree();
        tbt.setRoot(h1);
        h1.setLeft(h2);
        h1.setRight(h3);
        h2.setLeft(h4);
        h2.setRight(h5);
        h3.setLeft(h6);

        tbt.threadNodes(h1);

        //测试
        System.out.println(h5.getLeft());
        System.out.println(h5.getRight());
//        tbt.preOrder();
        System.out.println("线索化遍历的方式");
        tbt.threadList();
    }
}

//实现了线索化的二叉树
class ThreadBinaryTree{
    private HeroNode root;
    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre;

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

    //删除某个待定节点
    public void del(int no){
        if(root!=null){
            //这里需要立即判断
            if(this.root.getNo()==no){
                this.root=null;
                return;
            }else{
                this.root.del(no);
            }
        }else{
            System.out.println("树为空，不能删除");
        }

        this.root.del(no);
    }

    //编写对二叉树进行中序线索化的方法
    public void threadNodes(HeroNode hn){
        if(hn==null){
            return;
        }

        //先线索化左子树
        threadNodes(hn.getLeft());

        //线索化当前节点
        //处理当前节点的前驱节点
        if(hn.getLeft()==null){
            //让当前节点的左指针指向前驱节点
            hn.setLeft(pre);
            //修改当前节点的左指针的类型
            hn.setLeftType(1);
        }

        //处理后继节点
        if(pre !=null && pre.getRight()==null){
            pre.setRight(hn);
            pre.setRightType(1);
        }

        pre=hn;//每处理一个节点后，让当前节点是下一个节点的前驱节点
        //线索化右子树
        threadNodes(hn.getRight());
    }

    //遍历线索化二叉树的方法
    public void threadList(){
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode hn=root;

        while(hn!=null){
            while(hn.getLeftType()==0){
                hn=hn.getLeft();
            }
            System.out.println(hn);
            while(hn.getRightType()==1){
                hn=hn.getRight();
                System.out.println(hn);
            }
            hn=hn.getRight();
        }
    }
}

//构建节点信息
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//左子节点
    private HeroNode right;//右子节点

    //说明
    //如果leftype=0表示指向的是左子树，=1表示指向前驱节点
    //如果rightype=1表示指向的是右子树，=1表示指向后继节点
    private int leftType;
    private int rightType;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    //递归删除待定节点
    public void del(int no){
        if(this.left!=null && this.left.getNo()==no){
            this.left=null;
            return;
        }

        if(this.right!=null && this.right.getNo()==no){
            this.right=null;
            return;
        }

        if(this.left!=null){
            this.left.del(no);
        }

        if(this.right!=null){
            this.right.del(no);
        }
    }
}
