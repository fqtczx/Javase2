package fq.internet.tcp3;

import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket("10.162.77.226",12306);

        //键盘录入数据
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        //把通道内的数据包装一下
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        String line=null;
        while((line=br.readLine())!=null){
            //自定义结束标记
            if("886".equals(line)){
                bw.write(line);
                bw.newLine();
                bw.flush();
                break;
            }
            bw.write(line);
            bw.newLine();
            bw.flush();

            InputStream is=s.getInputStream();
            byte[] bys=new byte[1024];
            int len=is.read(bys);
            String str=new String(bys,0,len);

            System.out.println(str);
        }



        //bw.close();
        //br.close();
        s.close();

    }
}
