package ThreadDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qinc
 * @version V1.0
 * @Description: 中断响应
 * @Date 2017/12/28 18:06
 */
public class ReentrantLockTest2 {
    public static ReentrantLock lock1=new ReentrantLock();
    public static ReentrantLock lock2=new ReentrantLock();

    public  static  class IntLock implements Runnable{
        private int j=0;
        public IntLock(int j) {
            this.j=j;
        }

        @Override
        public void run() {
            try {
                if(j==1){
                    lock1.lockInterruptibly();
                    Thread.sleep(500);
                    lock2.lockInterruptibly();
                }else{
                    lock2.lockInterruptibly();
                    Thread.sleep(500);
                    lock1.lockInterruptibly();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getId()+"线程退出！");
            }

        }
    }

    public static void main(String args[]) throws InterruptedException {
        IntLock k1=new IntLock(1);
        IntLock k2=new IntLock(2);
        Thread t1=  new Thread(k1);
        Thread t2=  new Thread(k2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //线程相互等待，程序无法结束
        t2.interrupt();
        //线程t2主动中断，则线程顺利退出
    }
}
