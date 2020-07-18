package fq.huffmantree.zip;

import java.util.*;

public class HuffmanCode {
    static Map<Byte,String> huffmanCodes=new HashMap<>();
    static StringBuilder sb=new StringBuilder();

    public static void main(String[] args) {
        String str="i like like like java do you like a java";
        byte[] bytes=str.getBytes();
        /*
        System.out.println(bytes.length);

        List<Node> ns=stringToList(bytes);
        System.out.println(ns);

        Node node=huffmanTree(ns);//生成霍夫曼树,返回的是根节点
        node.preOrder();

        getCodes(node);
        System.out.println("生成的霍夫曼编码表"+huffmanCodes);
        byte[] huffmanCodeBytes=zip(bytes,huffmanCodes);
        */
        byte[] huffmanCodeBytes=huffmanZip(bytes);
        System.out.println(Arrays.toString(huffmanCodeBytes));
    }

    //将上面的代码封装成一个方法
    public static byte[] huffmanZip(byte[] bytes){
        List<Node> ns=stringToList(bytes);
        Node node=huffmanTree(ns);
        getCodes(node);
        byte[] huffmanCodeBytes=zip(bytes,huffmanCodes);
        return huffmanCodeBytes;
    }

    public static List<Node> stringToList(byte[] bytes){
        List<Node> list=new ArrayList<>();

//        char[] chs=str.toCharArray();
//        System.out.println(chs[1]);
        HashMap<Byte,Integer> ch=new HashMap();

        for(int i=0;i<bytes.length;i++){
            if(ch.get(bytes[i])==null){
                ch.put(bytes[i],1);
            }else{
                ch.put(bytes[i],ch.get(bytes[i])+1);
            }
        }
//        System.out.println(ch);

        Set chars=ch.keySet();
        for(Object character:chars){
            Node node=new Node((byte)character,ch.get(character));
            list.add(node);
        }

        return list;
    }

    //通过List创建赫夫曼树
    public static Node huffmanTree(List<Node> list){
          while(list.size()!=1){
            Collections.sort(list);
            Node left=list.get(0);
            Node right=list.get(1);
            Node root=new Node(null,left.weight+right.weight);
            root.left=left;
            root.right=right;
            list.remove(left);
            list.remove(right);
            list.add(root);
            Collections.sort(list);
        }
//        System.out.println(list);
        return list.get(0);
    }

    //调用方便，进行重载一下
    public static Map<Byte,String> getCodes(Node node){
        if(node==null){
            return null;
        }
        getCodes(node.left,"0",sb);
        getCodes(node.right,"1",sb);
        return huffmanCodes;
    }
    //生成霍夫曼编码表
    /**
     *
     * @param node 传入节点
     * @param code 路径，左边为0，右边为1
     * @param sb 用于拼接路径
     * @return
     */
    public static Map<Byte,String> getCodes(Node node, String code, StringBuilder sb){
        StringBuilder sb2=new StringBuilder(sb);
        //将code拼接到sb2
        sb2.append(code);
        if(node!=null){//如果node是null则不处理
            //判断node是叶子节点还是非叶子节点
            if(node.data==null){
                //递归处理
                //向左递归
                getCodes(node.left,"0",sb2);
                //向右递归
                getCodes(node.right,"1",sb2);
            }else{
                huffmanCodes.put(node.data,sb2.toString());
            }
        }
        return huffmanCodes;
    }

    //将字符串对应的byte[]数组通过生成的霍夫曼编码表生成二进制数组，但最终返回的还是字节数组
    //即将8位二进制数据变为一个字节,放进字节数组huffmanCodeBytes
    //huffmanCodeBytes[0]=10101000(补码)=>10100111(反码)=>11011000(原码)=-88
    //所以转变之后huffmanCodeBytes[0]=-88;
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        StringBuilder sb=new StringBuilder();
        for(byte b:bytes){
            sb.append(huffmanCodes.get(b));
        }
//        System.out.println(sb.toString());

        //统计最终返回的huffmanCodeBytes[]长度
        int len;
//        if(sb.length()%8==0){
//            len=sb.length()/8;
//        }else{
//            len=sb.length()/8+1;
//        }
        len=(sb.length()+7)/8;
        byte[] huffmanCodeBytes=new byte[len];
        int index=0;
        for(int i=0;i<sb.length();i+=8){
            String strByte;
            if(i+8>sb.length()){
                strByte=sb.substring(i);//最后几位是怎样进行补零的吗？
            }else{
                strByte=sb.substring(i,i+8);
            }
            huffmanCodeBytes[index]=(byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }
}

class Node implements Comparable<Node>{
    Byte data;//这里存储字符的ascii码
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排列
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //递归实现前序遍历
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