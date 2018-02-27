package ThreadDemo;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qinc
 * @version V1.0
 * @Description: 自定义线程池
 * @Date 2018/2/24 15:28
 */
public class MyThreadPool {




    private static LinkedList<Runnable> workList= new LinkedList<>();
    private LinkedList<Thread> threadsPool;
    static AtomicInteger  j=new AtomicInteger();//线程安全
    public MyThreadPool(){
        threadsPool=new LinkedList<>();
    }

    private  void  execute(Runnable job) throws InterruptedException {
        if(j.intValue()<3){
            workList.add(job);
            j.incrementAndGet();
        }else{
            synchronized (workList){
                System.out.println("线程池满了！wait");
                workList.wait();
            }
        }
    }
    public static void main(String args[]) throws InterruptedException {
        MyThreadPool threadPool= new MyThreadPool();
        for(int i=0;i<100;i++){
            threadPool.execute(new Job(i));
        }
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    Job job= (Job) workList.getFirst();
                    if(job!=null){
                        job.run();
                    }else{
                        synchronized (workList){
                            workList.notify();
                        }

                    }


                }
            }
        }).start();

        Thread.sleep(100000);
    }

}
class Job implements Runnable {
    int i=0;
    public Job(int i){
        this.i=i;
    }

    @Override
    public void run() {
        System.out.println(i);
    }
}