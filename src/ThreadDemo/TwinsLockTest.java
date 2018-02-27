package ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qinc
 * @version V1.0
 * @Description:  测试自定义同步组件
 * @Date 2018/2/26 16:40
 */
public class TwinsLockTest {

    public static void main(String args[]){

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        TwinsLock lock= new TwinsLock();
        for (int i=0;i<20;i++){
            executorService.submit(new Task(lock));
        }
        executorService.shutdown();

    }
}

class Task implements Runnable{
    TwinsLock lock;
    Task(TwinsLock lock){
        this.lock=lock;
    }
    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }
}