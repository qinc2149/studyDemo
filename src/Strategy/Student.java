package Strategy;

import java.util.concurrent.TimeUnit;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/11/14 15:38
 */
public class Student implements Person {

    private String name;
    public Student(String name) {
        this.name=name;
    }

    @Override
    public void giveMoney() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "上交班费50元");
    }

    @Override
    public void giveMoney1() {

    }
}
