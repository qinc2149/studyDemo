package ThreadDemo;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author qinc
 * @version V1.0
 * @Description: 允许多个线程同时访问：信号量（semaphore）
 * @Date 2017/12/29 14:52
 */
public class SemaphoreDemo implements Runnable{

    Semaphore semaphore = new Semaphore(5);//申明准入数为5的信号量
    @Override
    public void run() {
        try {
            semaphore.acquire();//获取一个准入许可
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+":doing");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]){

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        SemaphoreDemo demo = new SemaphoreDemo();

        for (int i=0;i<20;i++){
            executorService.submit(demo);
        }
        executorService.shutdown();
    }
}
