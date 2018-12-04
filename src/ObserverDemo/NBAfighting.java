package ObserverDemo;

import java.util.Observable;
import java.util.Observer;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/12/3 11:50
 */
public class NBAfighting implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("NBAfighting观察者收到订阅的消息"+arg);
    }
}
