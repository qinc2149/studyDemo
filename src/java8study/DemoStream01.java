package java8study;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/8/6 15:50
 */
public class DemoStream01 {
    public static void main(String args[]){
        List<Student> stList= new ArrayList<>();
        for(int i=0;i<100;i++){
            stList.add(new Student("stu"+i,20+i+(i%2)));
        }

        //1.filter筛选出叫stu1的学生
        stList.stream().filter(student -> student.getName().equals("stu1")).findFirst().ifPresent(d->{
            System.out.println(d.getName());
        });
        //2.distinct去重
        stList.stream().map(Student::getAge).distinct().forEach(System.out::println);
        //3.limt截取
        stList.stream().map(Student::getAge).distinct().limit(3).forEach(System.out::println);
        //4.skip跳过元素
        stList.stream().map(Student::getAge).distinct().skip(3).limit(3).forEach(System.out::println);
        //5.anyMatch 流中是否有一个元素可以匹配谓词
        if(stList.stream().anyMatch(student -> student.getAge()>50)){
            System.out.println("有年纪大于50的");
        };
        //6.allMatch 流中的所有元素是否都匹配谓词
        if(stList.stream().allMatch(student -> student.getAge()>50)){
            System.out.println("不是所有人的年纪都大于50");
        };
        //7.noneMatch 流中的所有元素是否都不匹配谓词
        if(stList.stream().noneMatch(student -> student.getAge()>50)){
            System.out.println("没有人大于50");
        };



    }
}
