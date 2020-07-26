package fq.internet.udp1;

import java.io.IOException;
import java.net.*;
import java.net.SocketException;

/*
UDP协议接收数据
创建接收端Socket对象
创建数据包，起到接收数据的作用
调用Socket对象的接收方法接收数据
解析数据并显示
释放资源
 */
public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        //这里使用指定接收端口的构造方法
        DatagramSocket dgs=new DatagramSocket(10086);

        //创建数据包，接收数据
        byte[] bys=new byte[1024];
        //这里使用的构造方法参数也是不同的
        //public DatagramPacket(byte[] buf,int length)
        DatagramPacket dp=new DatagramPacket(bys,bys.length);
        dgs.receive(dp);//receive方法，将得到的数据放入dp中

        //解析数据
        //得到发送端的IP地址主机名，是通过数据包dp得到的，因为里面包含了IP地址信息
        InetAddress ia=dp.getAddress();
        String address=ia.getHostAddress();
        System.out.println("address:"+ia);
        System.out.println("getHostAddress方法得到的地址:"+address);

        //dp的两个方法，分别是getData和getLength,分别得到数据缓冲区和数据长度
        byte[] bys2=dp.getData();
        int len=dp.getLength();
        //构建字符串
        String s=new String(bys2,0,len);
        System.out.println(s);

        //关闭
        dgs.close();
    }
}