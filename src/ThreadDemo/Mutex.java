package ThreadDemo;

import javax.print.DocFlavor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author qinc
 * @version V1.0
 * @Description: 自定义一个独占锁
 *                  1,需要实现lock接口
 *                  2,定义一个静态的内部类去集成AQS
 * @Date 2018/10/12 11:47
 */
public class Mutex implements Lock{

    //将操作代理到sync上
    private final Sync sync= new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    //静态内部类去继承AQS
    private static class Sync extends AbstractQueuedSynchronizer{

        //重写独占式获取同步状态
        @Override
        protected boolean tryAcquire(int acquires){
            if(compareAndSetState(0,1)){//设置成功表示获取了独占同步状态
                setExclusiveOwnerThread(Thread.currentThread());//设置当前线程拥有独占访问权。
                return true;
            }
            return false;
        }
        //重写独占式释放同步状态
        @Override
        protected boolean tryRelease(int arg) {
            if(getState()==0){//没有可以释放的
               throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }
}
