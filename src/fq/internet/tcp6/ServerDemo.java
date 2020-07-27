package fq.internet.tcp6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(12306);

        Socket s=ss.accept();

        //封装通道内的流
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("f.jpg"));
        BufferedInputStream bis=new BufferedInputStream((s.getInputStream()));

        BufferedWriter bwServer=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        byte[] bys=new byte[1024];
        int len=0;
        while((len=bis.read(bys))!=-1){
            bos.write(bys,0,len);

            OutputStream os=s.getOutputStream();
            os.write("massage has been received".getBytes());
        }

        bwServer.write("图片上传成功，谢谢您");
        bwServer.newLine();
        bwServer.flush();

//        br.close();
        s.close();
        //ss.close();
    }
}
