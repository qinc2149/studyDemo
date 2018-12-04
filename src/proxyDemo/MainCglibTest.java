package proxyDemo;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author qinc
 * @version V1.0
 * @Description: 程序入口
 * @Date 2018/11/22 10:45
 */
public class MainCglibTest {

    public static void main(String args[]){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(WXPay.class);
        enhancer.setCallback(new PayMethodInterceptor());

        WXPay proxyPay = (WXPay) enhancer.create();
        proxyPay.paySubmit();
        proxyPay.payNotify();
    }
}
