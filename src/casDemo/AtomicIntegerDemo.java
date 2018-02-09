package casDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qinc
 * @version V1.0
 * @Description: 无锁
 *   比较交换 CAS(V,E,N)  V:要修改的值 E：期望值 N：新值
 *   Atomic包是java虚拟机提供的无锁并发工具
 * @Date 2018/1/8 14:58
 */
public class AtomicIntegerDemo {

    static AtomicInteger  j=new AtomicInteger();//线程安全

    //static  Integer j=0;//非线程安全

    public static class addThread implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                j.incrementAndGet();
                //j++;
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread ts[]=new Thread[10];
        for (int i=0;i<10;i++){
            ts[i]=new Thread(new addThread());
        }
        for (int i=0;i<10;i++){
            ts[i].start();
        }
        for (int i=0;i<10;i++){
          ts[i].join();
        }

        System.out.println(j);
    }
}
