package ThreadDemo;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.lang.management.ThreadInfo;
import java.util.concurrent.*;

/**
 * @author qinc
 * @version V1.0
 * @Description: 使用BlockigQueue 实现生产消费（消息队列）
 * @Date 2018/1/8 17:01
 */
public class ProduceAndConsumeDemo {

    public static void main(String args[]){
        LinkedBlockingQueue queue= new LinkedBlockingQueue(10);
        ExecutorService ex= Executors.newCachedThreadPool();
        for(int i=0;i<3;i++){
            ex.execute(new Prodecer(queue));
        }
        for(int i=0;i<3;i++){
            ex.execute(new Consumer(queue));
        }

    }


}
class Prodecer implements Runnable{
    private BlockingQueue queue;
    public Prodecer(BlockingQueue queue){
        this.queue=queue;
    }
    @Override
    public void run() {
            try {
                for(int i=0;i<10;i++){
                    if(queue.offer(new QueueData("QINC",i+""),3, TimeUnit.MILLISECONDS)){
                        System.out.println("线程:"+Thread.currentThread().getId()+"插入数据成功！");
                    }else{
                        System.out.println("线程:"+Thread.currentThread().getId()+"队列已经满了！");
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       // }


    }
}

class Consumer implements Runnable{
    private BlockingQueue queue;
    public Consumer(BlockingQueue queue){
        this.queue=queue;
    }
    @Override
    public void run() {
       // while (true){
            try {
                System.out.println("线程："+Thread.currentThread().getId()+"消费数据"+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       // }

    }

}

class QueueData{
    private String name;

    private String age;

    public QueueData(String name,String age){
        this.name=name;
        this.age=age;
    }

    @Override
    public String toString() {
        return "QueueData{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
