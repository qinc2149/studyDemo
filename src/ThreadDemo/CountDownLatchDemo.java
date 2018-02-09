package ThreadDemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qinc
 * @version V1.0
 * @Description: 倒计时器，CountDownLatch，控制火箭发射（所有检查线程都结束后才可以主发射）
 * @Date 2017/12/29 16:18
 */
public class CountDownLatchDemo implements Runnable {
    static  CountDownLatch downLatch= new CountDownLatch(10);
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println(Thread.currentThread().getName()+"检查完成！");
            downLatch.countDown();//countDown表示该线程结束了 计数器减一
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[]) throws InterruptedException {
        CountDownLatchDemo demo = new CountDownLatchDemo();
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for(int i=0;i<11;i++){
            exec.submit(demo);
        }
        System.out.println(Thread.currentThread().getName()+"等待发射");
        downLatch.await();//等待倒计时器
        System.out.println(Thread.currentThread().getName()+"点火发射");
        exec.shutdown();


    }
}
