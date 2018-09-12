package shiyanlouJava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author qinc
 * @version V1.0
 * @Description: 1，函数式接口
 *               2，默认方法
 *               3，Optional类
 * @Date 2018/8/20 15:07
 */
public class NewFeaturesTester2 {

    public static void main(String args[]){
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("All of the numbers:");
        eval(list,n->true);
        System.out.println("Even numbers:");
        eval(list, n-> n%2 == 0 );
        System.out.println("Numbers that greater than  5:");
        eval(list, n -> n > 5 );

        //Option类
        Integer v1=null;
        Integer v2=new Integer(5);
        //ofNullable 允许传参时给出 null
        Optional<Integer> a=Optional.ofNullable(v1);
        // 如果传递的参数为null，那么 of 将抛出空指针异常（NullPointerException）
        Optional<Integer> b=Optional.of(v1);
        System.out.println(sum(a,b));


    }
    public static void eval(List<Integer> list, Predicate<Integer> predicate){
        for (Integer i:list){
            if(predicate.test(i)){
                System.out.println(i);
            };
        }

    }
    public static Integer sum(Optional<Integer> a,Optional<Integer> b){
        System.out.println("a:"+a.isPresent());
        System.out.println("b:"+b.isPresent());
        Integer v1=a.orElse(new Integer(0));
        Integer v2=b.get();
        return v1+v2;
    }
}
