package proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author qinc
 * @version V1.0
 * @Description: 支付代理类
 * @Date 2018/11/22 10:31
 */
public class PayInvocationHandler<PayProcessor>  implements InvocationHandler {

    //InvocationHandler持有的被代理对象
    PayProcessor target;
    public  PayInvocationHandler(PayProcessor target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行!!:"+method.getName());
        Object result=method.invoke(target,args);
        return result;
    }
}
