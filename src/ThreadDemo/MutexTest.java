package ThreadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author qinc
 * @version V1.0
 * @Description: 测试独占锁
 * @Date 2018/10/12 15:53
 */
public class MutexTest {
    static Mutex syncLock= new Mutex();
    public static void main(String args[]){

        new Thread(new MutexThread()).start();

        new Thread(new MutexThread()).start();



    }
    public  static  class MutexThread implements Runnable{
        @Override
        public void run() {
            syncLock.lock();
            System.out.println(Thread.currentThread().getId()+"获取锁");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            syncLock.unlock();
            System.out.println(Thread.currentThread().getId()+"释放锁");
        }
    }


}
