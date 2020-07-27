package fq.internet.tcp1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
TCP协议发送数据
创建发送端的Socket对象
获取输出流，写数据
释放资源
PS:接收端要先开启
 */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        //创建发送端的Socket对象
        //public Socket(InetAddress address,int port)
        //public Socket(String host,int port)
//        Socket s=new Socket(InetAddress.getByName("10.162.77.255"),12306);
        Socket s=new Socket("10.162.77.226",12306);

        //获取输出流，输出数据
        //public OutputStream getOutputStream()
        OutputStream os=s.getOutputStream();
        os.write("hello,我来了".getBytes());

        InputStream is=s.getInputStream();
        byte[] bys=new byte[1024];
        int len=is.read(bys);
        String str=new String(bys,0,len);

        System.out.println(str);

        s.close();
    }
}
