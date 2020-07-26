package fq.internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
一个类无构造方法
成员全部是静态的：Math、Arrays、Collections
单例设计模式：Runtime
类中有静态方法返回该类的对象
class Demo{
    private Demo(){
    }

    public static Demo getXXXXx(){
    return new Demo();
    }
}
 */
public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        //InetAddress中有一个成员方法
        //public static InetAddress getByName(String host)
        //根据给定的主机名或者IP地址的字符串表示得到IP地址对象
        InetAddress ia=InetAddress.getByName("www.baidu.com");
        InetAddress ia2=InetAddress.getByName("10.162.77.226");

        //获取两个东西，主机名和IP地址
        //public String getHostName()
        String hostName=ia2.getHostName();
        System.out.println(hostName);
        //public String getHostAddress()
        String hostAddress=ia2.getHostAddress();
        System.out.println(hostAddress);
    }
}
