package java8study;

import java.util.Collections;

/**
 * @author qinc
 * @version V1.0
 * @Description: java8的新特性
 *                  1.默认方法
 *                  2.函数式接口
 *                  3,每一个lambda都能够通过一个特定的接口(函数式接口)，与一个给定的类型进行匹配.
 * @Date 2018/8/24 16:31
 */
public class DefaultMethod {
    public static void main(String args[]){

        Formula formula=(a)->100*a;
        double cal1=formula.calculate(3);
        System.out.println(cal1);
        double sqrt1=formula.sqrt(100);
        System.out.println(sqrt1);

    }
}

@FunctionalInterface//注解可以加也可以不加，加了就只能有抽象方法。不然编译不过
interface Formula{

    double calculate(int a);
    default double sqrt(int a){
      return Math.sqrt(a);
    }

}

