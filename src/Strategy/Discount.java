package Strategy;

/**
 * @author qinc
 * @version V1.0
 * @Description: 打折算法
 * @Date 2018/2/22 11:32
 */
public class Discount extends PaySuper {
    @Override
    public void getMoney() {
        System.out.println("打折算法！");
    }
}
