package java8study;

import java.util.function.Function;

/**
 * @author qinc
 * @version V1.0
 * @Description: 方法和构造函数引用：Java 8 允许你通过::关键字获取方法或者构造函数的的引用
 * @Date 2018/8/27 11:03
 */
public class DemoLambda01 {
    public static void main(String args[]){
        //静态方法引用
         Converter<String,String> converter= Something::startsWith;
         String a=converter.convert("aABJ");
         System.out.println(a);
        //对象方法引用
        Something sm=new Something();
        Converter<String,String> converter1= sm::startsWith2;
        System.out.println(converter1.convert("SQJ"));
        //构造器方法引用
        SomethingFactory smFactory=Something::new;
        Something sm2=smFactory.create("java8");
        System.out.println(sm2);


        final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
        System.out.println(stringConverter.convert(2));     // 3


    }
}
class Something {

    private String name;
    public Something(){
    }
    public Something(String name){
        this.name=name;
    }

    static String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }

    String startsWith2(String s) {
        return String.valueOf(s.charAt(0));
    }

}

/**
 * 函数式接口
 * @param <F>
 * @param <T>
 */
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
@FunctionalInterface
interface SomethingFactory<P extends Something> {
    P create(String name);
}

