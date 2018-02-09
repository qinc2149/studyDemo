package ThreadDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.SimpleFormatter;

/**
 * @author qinc
 * @version V1.0
 * @Description: ThreadLoacl 简单使用
 * qinc注解：ThreadLoacl其实没啥特殊含义就是一种普通的数据类型而已，只是他把一个对象放在了当前线程的ThreadLocalMap中
 * 然后每个线程都有了自己私有的对象。ThreadLocalMap是Thread的一个属性
 * 问题：如果使用线程池的话，线程一直不会销毁，那么这个ThreadLocalMap越放越多,会内存溢出，下个demo说java虚拟机是怎么释放ThreadLocalMap
 * 中的资源的。。
 * @Date 2018/1/3 16:52
 */
public class ThreadLoaclDemo {

    //static final SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static  ThreadLocal<SimpleDateFormat> t1=new ThreadLocal<SimpleDateFormat>();

    private static class ParseDate implements Runnable{

        int i=0;
        public ParseDate(int i){
            this.i=i;
        }
        @Override
        public void run() {
            try {
                if(t1.get()==null){
                  t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                //Date t=sdf.parse("2018-01-02 16:57:"+(i%60));
                Date t=t1.get().parse("2018-01-02 16:57:"+(i%60));
                System.out.println(i+":"+t);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String args[]){
        ExecutorService es= Executors.newFixedThreadPool(10);
        for (int i=0;i<20;i++){
            es.execute(new ParseDate(i));
        }

    }
}
