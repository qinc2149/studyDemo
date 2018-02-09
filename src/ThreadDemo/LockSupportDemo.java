package ThreadDemo;

import java.util.concurrent.locks.LockSupport;

/**
 * @author qinc
 * @version V1.0
 * @Description: LockSupport 线程阻塞工具类
 * @Date 2017/12/29 17:47
 */
public class LockSupportDemo {

    public static Object u= new Object();

    public static  class  RunnableD implements Runnable {

        @Override
        public void run() {
            synchronized (u){
                System.out.println(Thread.currentThread().getName()+"执行ing");
                LockSupport.park();//线程挂起
                System.out.println(Thread.currentThread().getName()+"执行结束");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        RunnableD r1= new RunnableD();
        Thread t2= new Thread(r1);
        Thread t1= new Thread(r1);
        t1.start();
        t2.start();
        LockSupport.unpark(t1);//继续执行
        LockSupport.unpark(t2);
        t1.join();t2.join();
        System.out.println("主线程结束");


    }



}
