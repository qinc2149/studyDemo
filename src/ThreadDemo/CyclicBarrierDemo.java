package ThreadDemo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qinc
 * @version V1.0
 * @Description: 循环栅栏（CyclicBarrier） 当一组计数器慢的时候执行一个方法 可以循环利用
 * @Date 2017/12/29 17:03
 */
public class CyclicBarrierDemo {
    public static void main(String args[]){
        ExecutorService exce= Executors.newFixedThreadPool(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,new BarrierRun());
        for(int i=0;i<10;i++){
            System.out.println("soldier"+i+"报道");
            Soldier soldier= new Soldier("soldier"+i,cyclicBarrier);
            exce.submit(soldier);
        }


    }


}


class Soldier implements Runnable{
    private String soldierNm;
    private CyclicBarrier cyclicBarrier;

    public Soldier(String soldierNm, CyclicBarrier cyclicBarrier) {
        this.soldierNm = soldierNm;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();//等待所有线程开始到齐
            doWark();
            cyclicBarrier.await();//重新开始计数
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public void doWark(){
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println(soldierNm+"活干完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class BarrierRun implements  Runnable{

    @Override
    public void run() {
        System.out.println("cyclicBarrier 计数慢了开始执行这个方法");
    }

}