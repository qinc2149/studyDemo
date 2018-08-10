package java8study;


import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedInputStream;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/7/31 15:27
 */
public class Demo00 {

    public static void main(String args[]){
        List<Student> studentList=new ArrayList<>();
        for(int i=0;i<100;i++){
            studentList.add(new Student("stu"+i,20+(i%2)));
        }

        check ck= new check();
        for(Student st:studentList){
            if(ck.test(st)){
                System.out.println(st.getName());
            };
        }


    }
}
interface MyPredicate<T>{
    boolean test(T t);
}

class check implements MyPredicate<Student>{
    @Override
    public boolean test(Student o) {
        return o.getName().equals("stu20");
    }
}
