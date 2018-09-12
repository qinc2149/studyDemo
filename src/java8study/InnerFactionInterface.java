package java8study;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author qinc
 * @version V1.0
 * @Description: java8内置函数式接口
 * @Date 2018/8/27 11:52
 */
public class InnerFactionInterface {
    public static void main(String args[]){

        //Predicate
        Predicate<String> predicate=n->n.length()>0;
        System.out.println(predicate.test("qinc"));
        boolean isNull=predicate.negate().test("qinc");
        System.out.println(isNull);
        //Functions接口接收一个参数，并返回单一的结果.默认方法可以将多个函数串在一起（compse, andThen）
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123");     // "123"
        //Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数。
        Supplier<Student> personSupplier = Student::new;
        personSupplier.get();   // new Student
        //Consumer代表了在一个输入参数上进行操作
        Consumer<Student> getter=s->{
            System.out.println("name:"+s.getName());
        };
        getter.accept(new Student("qinc",27));
        //Comparator比较排序
        Comparator<Student> comparator=(s1,s2)->s1.getAge()-s2.getAge();
        Student s1=new Student("小米",22);
        Student s2=new Student("小明",28);
        System.out.println(comparator.compare(s1,s2));
        System.out.println(comparator.reversed().compare(s1,s2));
        //Optional不是一个函数式接口，而是一个精巧的工具接口
        Optional<String> optional = Optional.of("bam");
        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"
        optional.ifPresent(s -> System.out.println(s.charAt(0)));     // "b"


    }
}
