package ThreadDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qinc
 * @version V1.0
 * @Description: ThreadLoaclMap中的资源是如何释放的
 * @Date 2018/1/3 16:52
 */
public class ThreadLoaclDemo2 {

    //static final SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static  ThreadLocal<SimpleDateFormat> t1=new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected void finalize() throws Throwable {
            System.out.println("t1 is gc");
        }
    };

    static CountDownLatch downLatch= new CountDownLatch(10);
    private static class ParseDate implements Runnable{

        int i=0;
        public ParseDate(int i){
            this.i=i;
        }
        @Override
        public void run() {
            try {
                if(t1.get()==null){
                  t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                      @Override
                      protected void finalize() throws Throwable {
                          System.out.println(this.toString()+" is gc");
                      }
                  });
                }
                //Date t=sdf.parse("2018-01-02 16:57:"+(i%60));
                Date t=t1.get().parse("2018-01-02 16:57:"+(i%60));
                System.out.println(i+":"+t);
            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
                downLatch.countDown();
            }

        }
    }

    public static void main(String args[]) throws InterruptedException {
        ExecutorService es= Executors.newFixedThreadPool(5);
        for (int i=0;i<10;i++){
            es.execute(new ParseDate(i));
        }
        downLatch.await();
        t1=null;
        System.gc();


       //给ThreadLocalMap中set数据的时候会把无效的对象清除掉,后面就可以GC了
        Thread.sleep(2000);
        t1=new ThreadLocal<>();
       downLatch=new CountDownLatch(10);
        for (int i=0;i<10;i++){
            es.execute(new ParseDate(i));
        }
        downLatch.await();
        System.gc();

    }
}
