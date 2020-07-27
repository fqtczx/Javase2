package fq.internet.tcp3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(12306);

        Socket s=ss.accept();

        //封装通道内的流
        BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));

        //封装文本文件
        BufferedWriter bw=new BufferedWriter(new FileWriter("a.txt"));

        String line=null;
        while((line=br.readLine())!=null){
            bw.write(line);
            bw.newLine();
            bw.flush();

            OutputStream os=s.getOutputStream();
            os.write("massage has been received".getBytes());
        }

        bw.close();
//        br.close();
        s.close();
        //ss.close();
    }
}
