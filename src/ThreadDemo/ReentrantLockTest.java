package ThreadDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qinc
 * @version V1.0
 * @Description: synchronized的功能扩展：重入锁
 * @Date 2017/12/28 18:06
 */
public class ReentrantLockTest {
    public static int j=0;
    public static ReentrantLock lock=new ReentrantLock();

    public  static  class ReenterLock implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<1000000;i++){
                lock.lock();//加锁
                j++;
                lock.unlock();//释放锁
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        ReenterLock k1=new ReenterLock();
        Thread t1=  new Thread(k1);
        Thread t2=  new Thread(k1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(j);
    }
}
