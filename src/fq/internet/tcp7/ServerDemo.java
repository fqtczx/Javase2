package fq.internet.tcp7;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
多客户端是使用多线程来实现的
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(12306);

        while(true){
            Socket s=ss.accept();
            new Thread(new UserThread(s)).start();
        }

    }
}
