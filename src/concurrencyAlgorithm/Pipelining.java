package concurrencyAlgorithm;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.*;

/**
 * @author qinc
 * @version V1.0
 * @Description: 并行流水线，计算（B+C）*B/2
 * @Date 2018/1/10 15:55
 */
public class Pipelining {

    public static void main(String args[]) throws InterruptedException {
         new Thread(new AddTask()).start();
         new Thread(new RideTask()).start();
         new Thread(new ExceptTask()).start();
        for(int i=1;i<100;i++){
            for(int j=1;j<100;j++){
                Msg msg= new Msg();
                msg.setJ(j);
                msg.setI(i);
                msg.setOrgStr("("+i+"+"+j+")*"+i+"/2");
                AddTask.addQueue.put(msg);
            }
        }

    }
}

/**
 * 在线程间传递的信息载体
 */
class Msg{
    private double i;
    private double j;
    private String orgStr;
    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public String getOrgStr() {
        return orgStr;
    }

    public void setOrgStr(String orgStr) {
        this.orgStr = orgStr;
    }
}



/**
 * 计算除法的task
 */
class ExceptTask implements Runnable {

    //存放需要计算加法的数据
    public static BlockingQueue<Msg> exceptQueue=new LinkedBlockingQueue<>();

    @Override
    public void run() {
        try {
            while(true){
                Msg msg  = exceptQueue.take();
                msg.setJ(msg.getJ()/2);
                System.out.println(msg.getOrgStr()+"="+msg.getJ());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
/**
 * 计算乘法的task
 */
class RideTask implements Runnable {

    //存放需要计算加法的数据
    public static BlockingQueue<Msg> rideQueue=new LinkedBlockingQueue<>();

    @Override
    public void run(){
        try {
            while(true){
                Msg msg = rideQueue.take();
                msg.setJ(msg.getI()*msg.getJ());
                ExceptTask.exceptQueue.put(msg);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

/**
 * 计算加法的task
 */
class AddTask implements Runnable {

    //存放需要计算加法的数据
    public static BlockingQueue<Msg> addQueue=new LinkedBlockingQueue<>();

    @Override
    public void run()  {
        try {
            while(true){
                Msg msg = addQueue.take();
                msg.setJ(msg.getI()+msg.getJ());
                RideTask.rideQueue.put(msg);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}