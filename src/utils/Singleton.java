package utils;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/10/26 10:17
 */
public class Singleton {

    private static volatile  Singleton singleton;

    private Singleton(){//私有的构造方法

    }

    public Singleton getSingLeton(){//双重检查锁

        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton=new Singleton();
                }
            }
        }
        return singleton;

    }





}
enum Singleton7{
    instance;
    private String attribute;
    void setAttribute(String attribute){
        this.attribute = attribute;
    }
    String getAttribute(){
        return this.attribute;
    }
}

//静态内部类实现单例
class Singleton2{

    private Singleton2(){}

    private static class SingletonInstance {
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return SingletonInstance.INSTANCE;
    }



}