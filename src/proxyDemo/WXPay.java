package proxyDemo;

/**
 * @author qinc
 * @version V1.0
 * @Description: 实现类
 * @Date 2018/11/22 10:29
 */
public class WXPay implements PayProcessor {
    @Override
    public void paySubmit() {
        System.out.println("################### WXPay paySubmit ########################");
    }

    @Override
    public void payNotify() {

        System.out.println("################### WXPay payNotify ########################");

    }
}
