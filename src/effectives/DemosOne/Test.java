package effectives.DemosOne;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/12/7 17:20
 */
public class Test {

    public static void main(String ages[]){
        Teacher t=Teacher.newInstance("qinc","27");
        System.out.println(t.toString());
        Teacher t2=Teacher.newInstance("hyq","28");
        System.out.println(t==t2);


        PayService ps=PayService.newInstance();
        ps.paySubmit();
    }
}
