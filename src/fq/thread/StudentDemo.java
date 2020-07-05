package fq.thread;
/*
 * 学生类：Student
 * 设置学生类：SetThread(生产者)
 * 获取学生类：GetThread(消费者)
 * 测试类：StudentDemo
 */
/*
 * 需求：设置线程与获取线程使用的是同一个资源
 * 解决办法：在外界把这个数据创建出来，通过构造方法传递给其他的类
 * 问题二：加入了循环和判断，给出了不同的值
 * 1.同一个年龄出现多次：CPU的一点点执行权，就足够执行多条语句
 * 2.姓名和年龄不匹配：线程运行的随机性
 * 线程安全问题：
 * 1.是否是多线程环境
 * 2.是否有共享数据
 * 3.是否有多条语句操作共享数据
 * 解决方法：加锁,注意不同种类的线程都要加锁，且是同一把锁
 */
public class StudentDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student s=new Student();

        SetThread st=new SetThread(s);
        GetThread gt=new GetThread(s);

        Thread t1=new Thread(st);
        Thread t2=new Thread(gt);


        t2.start();
        t1.start();
    }

}

