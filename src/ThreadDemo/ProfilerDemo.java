package ThreadDemo;


/**
 * @author qinc
 * @version V1.0
 * @Description: ThreadLocal 既线程变量,是一个以ThreadLocal对象为键，以任意对象对值的存储结构，
 *               Thread类中有个ThreadLocalMap属性。保存这个该线程的私有变量
 *               ThreadLocal的set方法可以把键值存储结构放入线程ThreadLocalMap，可以通过get方法拿到
 * @Date 2018/2/24 10:35
 */
public class ProfilerDemo {


    private static final ThreadLocal<Long> TIME_THREADlOCAL=new ThreadLocal<>();


    private static void start(){
         TIME_THREADlOCAL.set(System.currentTimeMillis());
    }

    private static long end(){
       return System.currentTimeMillis()- TIME_THREADlOCAL.get();
    }


    public static void main(String args[]) throws InterruptedException {
        start();//在这里将系统时间set到了主线程的ThradLocalMap中
        Thread.sleep(1220);
        System.out.println(end());//在这里从主线程的ThradLocalMap中get到set进去的值
    }
}
