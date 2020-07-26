package fq.internet.udp2;

import java.io.IOException;
import java.net.*;

public class SendDemo2 {
    public static void main(String[] args) throws IOException {
        //创建发送端Socket对象
        DatagramSocket dgs=new DatagramSocket();

        //创建数据，并把数据打包
        //public DatagramPacket(byte[] buf,int length,InetAddress address,int port)
        //创建数据
        byte[] bys="你好啊，我来了".getBytes();
        DatagramPacket dp=new DatagramPacket(bys,bys.length,InetAddress.getByName("10.162.77.226"),12345);

        //调用Socket对象的发送方法发送数据
        //public void send(DatagramPacket p)
        //从这个套接字发送数据报包,DatagramPacket包括信息显示要发送的数据长度，远程主机的IP地址和端口号，远程主机。
        dgs.send(dp);
        dgs.close();
        System.out.println("数据发送成功");
    }
}
