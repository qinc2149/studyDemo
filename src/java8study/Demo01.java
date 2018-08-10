package java8study;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author qinc
 * @version V1.0
 * @Description: 1.debug 可以看出Stream的并行操作(流水线操作)
 *               2.还发现中间操作延迟执行 只有当有终端操作的时候才会触发中间操作执行
 * @Date 2018/7/31 10:38
 */
public class Demo01 {
    public static  void main(String args[]){
        List<Student> studentList=new ArrayList<>();
        for(int i=0;i<100;i++){
            studentList.add(new Student("stu"+i,20+(i%2)));
        }
        Stream st=studentList.stream().filter(stu ->{
            System.out.println("filtering"+stu.getAge());
            return stu.getAge()>20;
        }).map(stu->{
            System.out.println("maping"+stu.getName());
            return stu.getName();
        }).limit(10);
        List<String> sts= (List<String>) st.collect(Collectors.toList());
        System.out.println(sts.toString());


    }
}
class Student{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
