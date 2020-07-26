package fq.internet.udpkeyboard;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        //这里使用指定接收端口的构造方法
        DatagramSocket dgs=new DatagramSocket(12345);

        while(true) {
            //创建数据包，接收数据
            byte[] bys = new byte[1024];
            //这里使用的构造方法参数也是不同的
            //public DatagramPacket(byte[] buf,int length)
            DatagramPacket dp = new DatagramPacket(bys, bys.length);
            dgs.receive(dp);//receive方法，将得到的数据放入dp中

            //解析数据
            //得到发送端的IP地址主机名，是通过数据包dp得到的，因为里面包含了IP地址信息
            String address = dp.getAddress().getHostAddress();

            //dp的两个方法，分别是getData和getLength,分别得到数据缓冲区和数据长度
            //构建字符串
            String s = new String(dp.getData(), 0, dp.getLength());
            System.out.println("getHostAddress方法得到的地址:" + address);
            System.out.println(s);
        }

        //不用关闭，接收端应该一直开着
        //关闭
        //dgs.close();
    }
}
