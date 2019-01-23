package effectives.Demos2;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/12/17 18:13
 */
public class GenericTest {
    public static void main(String args[]){
        Box<Integer> box= new Box<>();
        box.setName("qinc");
        box.setAge(27);
        Box<String> box1= new Box<>();
        box1.setName("qinc");
        box1.setAge("27");
        sout(box);
        sout(box1);
        sout1(box);
        sout2(box);

    }
    //泛型方法，泛型通配符
    public static void sout(Box<?> box){
        System.out.println(box.getAge());
    }

    //泛型上线 表示通配符上限（？是number自己或者子类）
    public static void sout1(Box<? extends Number> box){
        System.out.println(box.getAge());
    }
    //泛型上线 表示通配符下限(?是Intrger的父类或者Integer本身)
    public static void sout2(Box<? super Integer> box){
        System.out.println(box.getAge());
    }




}


//泛型类
class Box<T>{

    private String name;

    private T age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getAge() {
        return age;
    }

    public void setAge(T age) {
        this.age = age;
    }
}

//泛型接口
interface  pay<T>{
    public void submit(T t);
}

class HFPay<T> implements pay<T>{

    @Override
    public void submit(T t) {
        System.out.println(t.getClass());
    }
}

