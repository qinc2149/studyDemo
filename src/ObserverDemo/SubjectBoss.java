package ObserverDemo;

import java.util.Observable;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/12/3 14:54
 */
public class SubjectBoss extends Observable {

    private String state;

    //状态改变
    public void payStateChange(String state){
        this.state=state;
        setChanged();
        notifyObservers(state);
    }

}