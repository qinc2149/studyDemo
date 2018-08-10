package java8study;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author qinc
 * @version V1.0
 * @Description: 数值流
 * @Date 2018/8/8 11:04
 */
public class DemoStream04 {

    public static void main(String args[]) {
        //1.数值范围
        IntStream intStream = IntStream.rangeClosed(1, 100);//生产一个1到100的数值流，包含结束值
        long count = intStream.filter(n -> n % 2 == 0).count();
        System.out.println("偶数个数是：" + count);
        //2.勾股数
       IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})).filter(t -> t[2] % 1 == 0).limit(5).forEach(m -> {
            System.out.println(m[0] + "," + m[1] + "," + m[2]);
        });

    }
}
