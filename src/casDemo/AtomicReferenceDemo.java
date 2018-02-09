package casDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author qinc
 * @version V1.0
 * @Description: 为金额小于20的人赠送20块钱，每个人只赠送一次
 * @Date 2018/1/8 15:24
 */
public class AtomicReferenceDemo {

    static AtomicReference<Integer> money = new AtomicReference<Integer>();
    public static class addMoney implements  Runnable{

        @Override
        public void run() {
           Integer m=money.get();
            if(m<20){
                if(money.compareAndSet(m,m+20)){
                    System.out.println(Thread.currentThread().getId()+"余额小于20，充值成功，现在余额为："+money.get());
                }else{
                    System.out.println("充值失败");
                }

            }else{
                System.out.println(Thread.currentThread().getId()+"余额大于20，无需充值");
            };
        }
    }

    public static void main(String args[]) throws InterruptedException {
        money.set(19);
        ExecutorService  es= Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            es.submit(new addMoney());
        }
    }
}
