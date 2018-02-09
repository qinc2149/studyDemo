package ThreadDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author qinc
 * @version V1.0
 * @Description: 测试线程异常
 * @Date 2018/1/2 15:54
 */
public class ThreadPoolTest {


    public static class DivTask implements  Runnable{

        int i;
        int j;
        public DivTask(int i,int j){
            this.i=i;
            this.j=j;
        }
        @Override
        public void run() {
            double re=j/i;
            System.out.println(re);
        }
    }

    public static void  main(String args[]) throws ExecutionException, InterruptedException {
        ExecutorService es= Executors.newFixedThreadPool(10);
        for(int i=0;i<5;i++){
            es.execute(new DivTask(i,100));
            /*Future future= es.submit(new DivTask(i,100));
            future.get();*/
        }
    }
}
