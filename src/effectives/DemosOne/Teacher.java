package effectives.DemosOne;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/12/7 17:15
 */
public class Teacher {
    private String name;
    private String age;

    private Teacher(String name, String age){
        this.age=age;
        this.name=name;
    }

    public static Teacher newInstance(String name,String age){
        return new Teacher(name,age);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
