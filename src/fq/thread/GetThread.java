package fq.thread;

public class GetThread implements Runnable {
    private Student s = null;

    public GetThread(Student s) {
        this.s=s;
    }

    @Override
    public void run() {
        while(true){
            synchronized (s) {
                if(!s.flag){
                    try {
                        s.wait();//线程等待的话，就会立马释放锁，将来也是从这里醒过来的
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(s.name+"-------"+s.age);
                    s.flag=false;
                    s.notify();
                }

            }
        }
    }
}

