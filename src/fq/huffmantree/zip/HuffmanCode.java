package fq.huffmantree.zip;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    static Map<Byte,String> huffmanCodes=new HashMap<>();
    static StringBuilder sb=new StringBuilder();

    public static void main(String[] args) {
        String str="我喜欢你";
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
        System.out.println(toBinString((byte)77,true));
        String decode=decode(huffmanCodeBytes);
        System.out.println(decode.length());

        String restring=deString(decode,huffmanCodes);
        System.out.println(restring);
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
                //其实这里最后几个不足8位时，应该强制将其补至8位，不让后面不好处理
                //但是在前面补0的话，就会破坏原有的编码意思
                //这就需要在后续的解码过程中将补到的0去掉
                System.out.println(strByte);//打印出最后不足8位的二进制字符串
            }else{
                strByte=sb.substring(i,i+8);
                System.out.println(strByte);
            }
            huffmanCodeBytes[index]=(byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //下面进行解压缩
    //首先将得到的字节数组重新转成二进制字符串
    public static String  toBinString(byte b,boolean flag){
        int temp=b;
        String str=Integer.toBinaryString(temp);
        if(str.length()>8){
            return str.substring(str.length()-8);
        }else{
            if(flag==true){//代表正数需要补位
                int count=8-str.length();
                for(int i=1;i<=count;i++){
                    str="0"+str;
                }
                return str;
            }else{//代表正数不需要补位
                return str;
            }

        }
    }

    public static String decode(byte[] bytes){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            if(i==bytes.length-1){
                sb.append(toBinString(bytes[i],false));//这里其实是有问题的
            }else{
                sb.append(toBinString(bytes[i],true));
            }
        }
        return sb.toString();
    }

    //将得到的二进制字符串对应霍夫曼编码表还原成原来的字符串
    public static String deString(String str,Map<Byte,String> huffmanCodes){
        Map<String,Byte> map=new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
            //将编码表的map进行反转，以便进行查找
        }
        System.out.println(map);

        //接下来进行遍历字符串，进行还原字符串
        //这里使用双指针进行遍历
        ArrayList<Byte> al=new ArrayList<Byte>();
        for(int i=0,j=1;i<str.length() && j<str.length();){
            String s=String.valueOf(str.charAt(i));
            while(map.get(s)==null){
                s=s+String.valueOf(str.charAt(j));
                j++;
            }

            al.add(map.get(s));
            i=j;
            j++;
        }

        byte[] bys=new byte[al.size()];
        int index=0;
        for(Byte b:al){
            bys[index]=b;
            index++;
        }

        return new String(bys);
    }

    //编写一个方法，对文件进行压缩
    public static void zipFile(String srcFile,String desFile) throws IOException {
        FileInputStream fis= new FileInputStream(srcFile);
        byte[] b=new byte[fis.available()];
        fis.read(b);
        byte[] huffmanBytes=huffmanZip(b);

        FileOutputStream fos=new FileOutputStream(desFile);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(huffmanBytes);
        oos.writeObject(huffmanCodes);

        fis.close();
        fos.close();
        oos.close();
    }

    //编写一个方法，实现文件的解压
    public static void deZip(String zipFile,String desFile) throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream(zipFile);
        ObjectInputStream ois=new ObjectInputStream(fis);

        byte[] huffmanBytes=(byte[])ois.readObject();
        Map<Byte,String> map=(Map)ois.readObject();

        String decode=decode(huffmanBytes);
        //这个代码么有完成，前面的代码没有考虑方法返回值，导致不具有普适性
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