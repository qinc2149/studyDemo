package proxyDemo;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author qinc
 * @version V1.0
 * @Description: CGLIB动态代理
 * @Date 2018/11/22 16:31
 */
public class PayMethodInterceptor  implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理执行!!:"+method.getName());
        Object result= methodProxy.invokeSuper(o,objects);
        return result;
    }
}
