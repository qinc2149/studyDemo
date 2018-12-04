package Strategy;

/**
 * @author qinc
 * @version V1.0
 * @Description: 打印方法的执行时间
 * @Date 2018/11/14 15:40
 */
public class MonitorUtil {

    static ThreadLocal<Long> t= new ThreadLocal<>();
    public static void start(){
        t.set(System.currentTimeMillis());
    }

    public static void finish(String methodNm){
        long finishTime=System.currentTimeMillis();
        System.out.println("方法："+methodNm+"耗时："+(finishTime-t.get()));
    }



}
