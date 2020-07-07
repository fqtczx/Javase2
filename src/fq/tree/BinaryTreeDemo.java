package fq.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {

    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode hn){
        root=hn;
    }

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
    public void add(HeroNode hn){
        if(root==null){
            root=hn;
        }else{

        }
    }
}
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

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

    public void preOrder(){
        System.out.println(this.toString());
        if(left!=null){
            this.left.preOrder();
        }
        if(right!=null){
            this.right.preOrder();
        }
    }

    public void infixOrder(){
        if(left!=null){
            this.left.infixOrder();
        }
        System.out.println(this.toString());
        if(right!=null){
            this.right.infixOrder();
        }
    }

    public void postOrder(){
        if(left!=null){
            this.left.postOrder();
        }
        if(right!=null){
            this.right.postOrder();
        }
        System.out.println(this.toString());

    }
}
