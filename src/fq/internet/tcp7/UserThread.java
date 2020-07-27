package fq.internet.tcp7;

import java.io.*;
import java.net.Socket;

public class UserThread implements Runnable{

    private Socket s;

    public UserThread(Socket s){
        this.s=s;
    }
    @Override
    public void run() {
        try{
            //封装通道内的流
            BufferedWriter bw=new BufferedWriter(new FileWriter(System.currentTimeMillis()+".txt"));
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
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
