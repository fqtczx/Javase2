package fq.thread;
public class SetThread implements Runnable {
    private Student s=null;
    private int x=0;

    public SetThread(Student s) {
        this.s=s;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (s) {
                if (x % 2 == 0) {
                    s.name = "张三";
                    s.age = 27;
                } else {
                    s.name = "李四";
                    s.age = 28;
                }
                x++;
            }
        }
    }
}
