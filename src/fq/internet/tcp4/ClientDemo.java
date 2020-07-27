package fq.internet.tcp4;

import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket("10.162.77.226",12306);

        BufferedReader br=new BufferedReader(new FileReader("a.txt"));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        String line=null;
        while((line=br.readLine())!=null){
            bw.write(line);
            bw.newLine();
            bw.flush();

            InputStream is=s.getInputStream();
            byte[] bys=new byte[1024];
            int len=is.read(bys);
            String str=new String(bys,0,len);

            System.out.println(str);
        }

        br.close();
        s.close();
    }
}
