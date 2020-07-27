package fq.internet.tcp4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(12306);

        Socket s=ss.accept();

        //封装通道内的流
        BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));

        String line=null;
        while((line=br.readLine())!=null){
            System.out.println(line);

            OutputStream os=s.getOutputStream();
            os.write("massage has been received".getBytes());
        }

//        br.close();
        s.close();
        //ss.close();
    }
}
