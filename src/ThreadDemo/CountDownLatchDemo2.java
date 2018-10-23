package ThreadDemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qinc
 * @version V1.0
 * @Description: 倒计时器，CountDownLatch，自己实现的countDownLatch 有点小问题
 * @Date 2017/12/29 16:18
 */
public class CountDownLatchDemo2 implements Runnable {
    static  CountDownLotchAQS downLatch= new CountDownLotchAQS();
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
        CountDownLatchDemo2 demo = new CountDownLatchDemo2();
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for(int i=0;i<11;i++){
            exec.submit(demo);
        }
        System.out.println(Thread.currentThread().getName()+"等待发射");
        downLatch.waits();//等待倒计时器
        System.out.println(Thread.currentThread().getName()+"点火发射");
        exec.shutdown();


    }
}
