package casDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author qinc
 * @version V1.0
 * @Description: AtomicStampedReference,增加了时间戳，防止在充值过程中余额被消费产生的多次充值操作
 * @Date 2018/1/8 15:24
 */
public class AtomicStampedReferenceDemo {

    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19,0);
    public static class addMoney implements  Runnable{

        @Override
        public void run() {
            Integer m=money.getReference();
            int timeStamp=money.getStamp();
            if(m<20){
                if(money.compareAndSet(m,m+20,timeStamp,timeStamp+1)){
                    System.out.println(Thread.currentThread().getId()+"余额小于20，充值成功，现在余额为："+money.getReference());
                }else{
                    System.out.println("充值失败");
                }

            }else{
                System.out.println(Thread.currentThread().getId()+"余额大于20，无需充值");
            };
        }
    }

    public static void main(String args[]) throws InterruptedException {
        ExecutorService  es= Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            es.submit(new addMoney());
        }
    }
}
