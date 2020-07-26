package fq.internet.chatroom;

import fq.internet.udpkeyboard.ReceiveDemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
通过多线程改进程序，这样就可以同时发送就收
 */
public class ChatRoom {
    public static void main(String[] args) throws SocketException {
        DatagramSocket dsSend=new DatagramSocket();
        DatagramSocket dsReceive=new DatagramSocket(12306);

        SendThread st=new SendThread(dsSend);
        ReceiveThread rt=new ReceiveThread(dsReceive);

        Thread t1=new Thread(st);
        Thread t2=new Thread(rt);

        t1.start();
        t2.start();
    }
}
