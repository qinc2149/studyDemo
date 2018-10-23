package ThreadDemo;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author qinc
 * @version V1.0
 * @Description: 利用AQS自己实现CountDownLotch
 * @Date 2018/10/10 15:56
 */
public class CountDownLotchAQS {

    Sync sync = new Sync(10);

    public void countDown() throws InterruptedException {
        sync.releaseShared(1);
    }

    public void  waits() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }




    //自定义队列同步器
    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if(count<=0){
                throw new IllegalArgumentException("count 必须大于0");
            }
            setState(count);//set同步状态
        }
        //获取恭喜状态
        @Override
        protected int tryAcquireShared(int acquires) {
            return (getState() == 0) ? 1 : -1;
        }

        //释放共享状态
        @Override
        protected boolean tryReleaseShared(int releases) {
            // Decrement count; signal when transition to zero
            for (;;) {
                int c = getState();
                if (c == 0) {
                    return false;
                }
                int nextc = c-1;
                if (compareAndSetState(c, nextc)) {
                    return nextc == 0;
                }
            }
        }



    }

}


