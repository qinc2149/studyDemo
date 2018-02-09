package ThreadDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qinc
 * @version V1.0
 * @Description: synchronized是不公平锁 reentrantLock可以实现公平锁
 * @Date 2017/12/28 18:06
 */
public class ReentrantLockTest4 {
    public static ReentrantLock lock=new ReentrantLock(true);//公平锁

    public  static  class FairLock implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+"获得锁");
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName()+"释放锁");
                }

            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        FairLock k1=new FairLock();
        Thread t1=  new Thread(k1);
        Thread t2=  new Thread(k1);
        t1.start();
        t2.start();
    }
}
