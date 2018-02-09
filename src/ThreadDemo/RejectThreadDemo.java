package ThreadDemo;

import java.util.concurrent.*;

/**
 * @author qinc
 * @version V1.0
 * @Description: 自定义线程池拒绝策略,所有的拒绝策略都实现了RejectedExecutionHandler接口
 * @Date 2018/1/2 14:26
 */
public class RejectThreadDemo {

    public static class MyTask implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis()+"---ThreadId:"+Thread.currentThread().getId());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[]) throws InterruptedException {
        MyTask task = new MyTask();

        ExecutorService ex=new ThreadPoolExecutor(5,5,0L,TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString()+"is discarded");
                    }
                });

        for(int i=0;i<Integer.MAX_VALUE;i++){
            ex.submit(task);
            Thread.sleep(10);
        }
    }
}
