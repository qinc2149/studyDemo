package ThreadDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qinc
 * @version V1.0
 * @Description:  reentrantLock重入锁的好搭档 condition 条件
 * @Date 2017/12/28 18:06
 */
public class ReentrantLockCondition {
    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition=lock.newCondition();

    public  static  class FairLock implements Runnable{
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得锁");
                condition.await();//类似于wait方法 使当前线程等待 别且释放当前锁资源
                System.out.println(Thread.currentThread().getName()+"正在做一些事情！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放锁");
            }

        }
    }

    public static void main(String args[]) throws InterruptedException {
        FairLock k1=new FairLock();
        Thread t1=  new Thread(k1);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        lock.unlock();
        System.out.println("主线程结束");
    }
}
