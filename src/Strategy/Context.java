package Strategy;

/**
 * @author qinc
 * @version V1.0
 * @Description: 策略模式分装的是算法，根据收款类型选择不同的的算
 *               感觉策略模式和简单工厂模式很像，他们的区别是啥。。
 *               1，通过工厂返回一个你需要的对象，然后调用对象的方法，对于使用者来说需要知道具体的算法对象
 *               2，策略模式是你需要自己先有一个算法对象，然后把对象给策略类去使用。对于使用者来说只需要知道策略对像就可以了，不需要知道算法对象
 *               3，他们的区别好像不是很大，看需求场景选择不同的模式
 * @Date 2018/2/22 11:34
 */
public class Context {

    private PaySuper paySuper;

    public Context(String type) {
        switch (type){
            case "打折":
                paySuper=new Discount();
                break;
            case "满减":
                paySuper=new Fullcut();
                break;
        }
    }

    public void getRestlt(){
        paySuper.getMoney();
    }
}
