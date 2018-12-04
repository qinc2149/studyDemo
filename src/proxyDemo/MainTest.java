package proxyDemo;

import Strategy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author qinc
 * @version V1.0
 * @Description: 程序入口
 * @Date 2018/11/22 10:45
 */
public class MainTest {

    public static void main(String args[]){
        //创建一个实例对象，这个对象是被代理的对象
        PayProcessor payProcessor= new ZFBPay();
        //创建一个与代理对象相关联的InvocationHandler
        InvocationHandler invocationHandler= new PayInvocationHandler<>(payProcessor);
        //创建一个代理对象
        PayProcessor proxyPay= (PayProcessor) Proxy.newProxyInstance(
                PayProcessor.class.getClassLoader(),
                new Class<?>[]{PayProcessor.class},
                invocationHandler
        );
        //通过代理类去支付
        proxyPay.paySubmit();
        proxyPay.payNotify();
    }
}
