package ThreadDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author qinc
 * @version V1.0
 * @Description: 自定义同步组件，共享式获取同步状态
 * @Date 2018/2/26 16:04
 */
public class TwinsLock implements Lock {

    //自定义队列同步器
    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if(count<=0){
                throw new IllegalArgumentException("count 必须大于0");
            }
            setState(count);//set同步状态
        }
        //重写同步器提供的模板方法，由于我们用的是共享式获取同步状态
        @Override
        public int tryAcquireShared(int reduceCount){
            //
            for(;;){
                int current=getState();//获取当前的同步状态
                int newCurrent=current-reduceCount;//新状态
                if(newCurrent>=0 && compareAndSetState(current,newCurrent)){
                    return newCurrent;
                }
            }
        }
        @Override
        public boolean tryReleaseShared(int returnCount){
            for(;;){
                int current = getState();//获取当前的同步状态
                int newCount = current + returnCount;//新状态
                if (compareAndSetState(current, newCount)) {//设置新状态
                    return true;
                }
            }
        }

    }

    private Sync sync= new Sync(5);//同时有两个线程可以获取锁
    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }


    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

}
