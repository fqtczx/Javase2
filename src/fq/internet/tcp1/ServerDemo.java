package fq.internet.tcp1;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
TCP协议接收数据
创建接收端的Socket对象
监听客户端连接，返回一个对应的Socket对象
获取输入流，读取数据并显示在控制台
释放资源
PS:接收端要先开启
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        //创建接收端的Socket对象
        //public ServerSocket(int port)
        ServerSocket ss=new ServerSocket(12306);

        //监听客户端连接，返回一个对应的Socket对象
        //public Socket accept()
        Socket s=ss.accept();//阻塞式方法

        //获取输入流，读取数据并显示在控制台
        InputStream is=s.getInputStream();

        byte[] bys=new byte[1024];
        int len=is.read(bys);
        String str=new String(bys,0,len);
        String ip=s.getInetAddress().getHostAddress();

        System.out.println("from ip is:"+ip+" and massage is:"+str);

        OutputStream os=s.getOutputStream();
        os.write("massage has been received".getBytes());

        s.close();
        //ss.close();
    }
}
