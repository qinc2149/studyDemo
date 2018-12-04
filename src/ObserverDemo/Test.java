package ObserverDemo;

import java.util.Observable;
import java.util.Observer;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/12/3 14:56
 */
public class Test {
    public static void main(String args[]){
        //被观察着
        SubjectBoss subjectBoss= new SubjectBoss();
        //观察者1
        Observer observer1= new NBAfighting();
        //观察者2
        Observer  observer2= new LOLfighting();
        //用于注册新的观察者对象到向量中
        subjectBoss.addObserver(observer1);
        subjectBoss.addObserver(observer2);
        //通知方法，用于在方法内部循环调用向量中每一个观察者的update()方法。
        subjectBoss.payStateChange("success");
        subjectBoss.payStateChange("fail");
        subjectBoss.payStateChange("paying");
    }
}
