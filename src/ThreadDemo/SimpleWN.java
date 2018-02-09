package ThreadDemo;

/**
 * @author qinc
 * @version V1.0
 * @Description: wait() 与 notify() 方法
 * 注意：Thread.sleep(3000);
 * @Date 2017/12/26 17:17
 */
public class SimpleWN {

    static Object obj =new Object();

    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println("Thread T1 start....");
                try {
                    System.out.println("Thread T1 wait....");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread T1 end....");
            }
        }
    }


    public static class T2 extends Thread{
        @Override
        public void run() {
            System.out.println("Thread T2 start....");
            synchronized (obj){
                System.out.println("Thread T2 notify....");
                obj.notify();
                System.out.println("Thread T2 end....");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]){
        T1 t1 =new T1();
        T2 t2=new T2();
        t1.start();
        t2.start();
    }


}
