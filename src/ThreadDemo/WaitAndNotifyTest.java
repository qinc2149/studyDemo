package ThreadDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/2/23 16:51
 */
public class WaitAndNotifyTest {

    public static void main(String args[]){
        List<String> pool=new ArrayList<>();
        Object lock= new Object();

        //生产
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(pool.size()<10){
                        System.out.println("生产了一个产品");
                        pool.add("产品");
                    }else{
                        try {
                            System.out.println("队列满了，不在生产了");
                            synchronized (lock){
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        //消费
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (pool.size()==10){
                        System.out.println("一次全部消费完了");
                        pool.clear();
                        synchronized (lock){
                            System.out.println("通知工厂继续生产！");
                            lock.notify();
                        }

                    }
                }
            }
        }).start();

    }

}

