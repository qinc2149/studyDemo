package ThreadDemo;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author qinc
 * @version V1.0
 * @Description: 读写锁
 * @Date 2017/12/29 15:22
 */
public class ReadWriteLockDemo {

    private static Lock lock = new ReentrantLock();//普通重入锁

    private static  ReadWriteLock readWriteLock= new ReentrantReadWriteLock();

    private static Lock readLock=readWriteLock.readLock();//读锁

    private static Lock writeLock=readWriteLock.writeLock();//写锁

    private  int value;

    //读
    public int handleRead(Lock lock) {
       try {
           lock.lock();
           Thread.sleep(1000);
           return value;
       }catch (InterruptedException e){
            e.printStackTrace();
       }finally {
           lock.unlock();
       }
        return 0;
    }
    //写
    public void handleWrite(Lock lock,int index){
        try {
            lock.lock();
            Thread.sleep(2000);
            this.value=index;
            System.out.println(Thread.currentThread().getName()+"写数据"+index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String args[]){
        final ReadWriteLockDemo demo =new ReadWriteLockDemo();

        Runnable readRunnable=new Runnable() {
            @Override
            public void run() {
                try {
                    //int readV=demo.handleRead(lock);
                    int readV=demo.handleRead(readLock);
                    System.out.println(Thread.currentThread().getName()+"读取数据"+readV);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable=new Runnable() {
            @Override
            public void run() {
                int writeV=new Random().nextInt(10000);
               // demo.handleWrite(lock,writeV);
                demo.handleWrite(writeLock,writeV);

            }
        };

        for(int i=18;i<20;i++){
            new Thread(writeRunnable).start();
        }

        for(int i=0;i<18;i++){
            new Thread(readRunnable).start();
        }



    }
}
