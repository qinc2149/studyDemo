package ThreadDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qinc
 * @version V1.0
 * @Description: 锁申请等待限时
 * @Date 2017/12/28 18:06
 */
public class ReentrantLockTest3 {
    public static int j=0;
    public static ReentrantLock lock=new ReentrantLock();

    public  static  class ReenterLock implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                try {
                    lock.tryLock(5, TimeUnit.SECONDS);//加锁
                    System.out.println(Thread.currentThread().getName()+"获得锁");
                    Thread.sleep(6000);
                    j++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();//释放锁
                    System.out.println(Thread.currentThread().getName()+"释放锁");
                }

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
