package java8study;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author qinc
 * @version V1.0
 * @Description: 创建流
 * @Date 2018/8/8 14:15
 */
public class DemoStream05 {
    public static void  main(String args[]){

        //1.由值创建流
        Stream<String> stringStream=Stream.of("Java8","c++","c#","js","python","go");
        stringStream.map(String::toUpperCase).forEach(System.out::println);
        //2.获取一个空的流
        Stream<String> emptyStream=Stream.empty();
        emptyStream.forEach(System.out::println);
        //3.由数组创建流
        int[] numbers={1,4,78,100,12};
        int sum= Arrays.stream(numbers).sum();
        System.out.println(sum);
        //4.由文件生成流
        try {
            Stream<String> lines=Files.lines(Paths.get("src/java8study/data.txt"), Charset.defaultCharset());
            lines.flatMap(line->Arrays.stream(line.split(" "))).distinct().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //5.由函数生成流。创建无限流
        //需要以此生成一系列值的时候应该使用iterate
        Stream.iterate(0,n->n+2).limit(10).forEach(System.out::println);
        //创建斐波纳契元组序列
        Stream.iterate(new int[]{0,1},n->new int[]{n[1],n[0]+n[1]}).limit(10).forEach(t->{
            System.out.println("("+t[0]+","+t[1]+")");
        });
        //创建斐波纳契数列
        Stream.iterate(new int[]{0,1},n->new int[]{n[1],n[0]+n[1]}).limit(20).map(m->m[1]).forEach(t->{
            System.out.print(t+",");
        });
        //generate也可以按需生成一个无限流，但是generate不是依次对每个新生产的值应用函数
        Stream.generate(Math::random).limit(5).forEach(System.out::println);


    }
}
