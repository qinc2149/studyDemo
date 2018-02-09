package futureSelfRealize;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qinc
 * @version V1.0
 * @Description: FutureData 包装 RealData
 * @Date 2018/1/9 16:25
 */
public class FutureData implements Data {
    public static ReentrantLock setlock=new ReentrantLock();
    public static ReentrantLock getlock=new ReentrantLock();
    public static Condition getcond=getlock.newCondition();
    private RealData realData;
    private boolean isReady=false;


    public  void setRealData(RealData realData) {
        if(isReady){//准备好了
            return;
        }
        setlock.lock();
        this.realData = realData;
        isReady=true;
        setlock.unlock();
        getlock.lock();
        getcond.signal();
        getlock.unlock();




    }

    @Override
    public  String getResult() {
        getlock.lock();
        while(!isReady){//没准备好
            try {
                getcond.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        getlock.unlock();
        return realData.getResult();

    }
}
