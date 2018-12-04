package effectives.Concurrency;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/11/29 15:32
 */
public class A {
    public String name="fff";
    public void ss(){
        System.out.println("ffff");
    }
    public static void main(String args[]){
        A a1= new b();
        a1.ss();
        System.out.println(a1.name);
    }
}

class  b extends A{
    @Override
    public void ss(){
        System.out.println("zzzz");
    }
    public String name="zzz";
}
