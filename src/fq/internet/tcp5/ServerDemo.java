package fq.internet.tcp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(12306);

        Socket s=ss.accept();

        //封装通道内的流
        BufferedWriter bw=new BufferedWriter(new FileWriter("c.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));

        BufferedWriter bwServer=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        String line=null;
        while((line=br.readLine())!=null){
//            if (line.equals("over")) {
//                break;
//            }
            bw.write(line);
            bw.newLine();
            bw.flush();

            OutputStream os=s.getOutputStream();
            os.write("massage has been received".getBytes());
        }

        bwServer.write("文件上传成功，谢谢您");
        bwServer.newLine();
        bwServer.flush();

//        br.close();
        s.close();
        //ss.close();
    }
}
