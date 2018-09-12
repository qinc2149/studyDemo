package shiyanlouJava8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinc
 * @version V1.0
 * @Description: 1，Lambda表达式的语法
 *               2，方法引用
 * @Date 2018/8/20 15:07
 */
public class NewFeaturesTester {

    public static void main(String args[]){

        NewFeaturesTester tester= new NewFeaturesTester();

        //带有类型声明的表达式
        MathOperation addition=(int a,int b)->a+b;
        //没有类型申明的表达式
        MathOperation subtraction=(a,b)->a-b;
        //带有大括号，带有返回语句的表达式
        MathOperation multiplication=(int a,int b)->{return a*b;};
        //没有大括号，没有返回语句的表达式
        MathOperation division=(a,b)->a/b;

        // 输出结果
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 没有括号的表达式
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 有括号的表达式
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        // 调用sayMessage方法输出结果
        greetService1.sayMessage("Shiyanlou");
        greetService2.sayMessage("Classmate");

        //方法的引用 ::
        List names = new ArrayList();
        names.add("Peter");
        names.add("Linda");
        names.add("Smith");
        names.add("Zack");
        names.add("Bob");
        //引用PrintStream的println方法
        names.forEach(System.out::println);
    }


    //函数接口是一种只有一个方法的接口,可以加注解，也可以不加
    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);


    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}
