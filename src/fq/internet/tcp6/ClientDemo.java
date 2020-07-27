package fq.internet.tcp6;

import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket("10.162.77.226",12306);

        BufferedInputStream bis=new BufferedInputStream(new FileInputStream("g.jpg"));
        BufferedOutputStream bos=new BufferedOutputStream(s.getOutputStream());

        byte[] bys=new byte[1024];
        int len=0;
        while((len=bis.read(bys))!=-1){
            bos.write(bys,0,len);
            bos.flush();

            InputStream is=s.getInputStream();
            byte[] byss=new byte[1024];
            int lent=is.read(byss);
            String str=new String(byss,0,len);

            System.out.println(str);
        }

        s.shutdownOutput();

        BufferedReader brClient=new BufferedReader(new InputStreamReader(s.getInputStream()));
        String st=brClient.readLine();
        System.out.println(st);

        bis.close();
        s.close();
    }
}
