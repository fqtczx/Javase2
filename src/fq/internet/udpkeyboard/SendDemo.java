package fq.internet.udpkeyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SendDemo {
    public static void main(String[] args) throws IOException {
        /*
        这次的发送数据来自键盘录入
         */
        //创建发送端Socket对象
        DatagramSocket dgs=new DatagramSocket();

        //创建数据，并把数据打包
        //public DatagramPacket(byte[] buf,int length,InetAddress address,int port)
        //创建数据
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line=null;
        while((line=br.readLine())!=null){
            if(line.equals("886")){
                break;
            }
            byte[] bys=line.getBytes();
            //DatagramPacket dp=new DatagramPacket(bys,bys.length, InetAddress.getByName("10.162.77.226"),12345);
            DatagramPacket dp=new DatagramPacket(bys,bys.length, InetAddress.getByName("10.162.77.255"),12345);

            //调用Socket对象的发送方法发送数据
            //public void send(DatagramPacket p)
            //从这个套接字发送数据报包,DatagramPacket包括信息显示要发送的数据长度，远程主机的IP地址和端口号，远程主机。
            dgs.send(dp);

            System.out.println("数据发送成功");
        }
        dgs.close();
    }
}
