package ThreadDemo;

import java.util.concurrent.*;

/**
 * @author qinc
 * @version V1.0
 * @Description: 扩展线程池
 * @Date 2018/1/2 14:50
 */
public class ExtThreadPool {

    public static class MyTask implements Runnable{
        public String name;
        public MyTask(String name){
            this.name=name;
        }

        @Override
        public void run() {
            try {
                System.out.println("正在执行线程id："+Thread.currentThread().getId()+"---Task name is"+name);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]){


        ExecutorService es=new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>()){

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行"+((MyTask) r).name);
            }
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成"+((MyTask) r).name);
            }
            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i=0;i<10;i++){
            es.execute(new MyTask("Taks"+i+1));
        }
        es.shutdown();

    }
}
