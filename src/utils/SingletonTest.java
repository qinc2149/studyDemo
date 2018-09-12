package utils;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/9/12 17:20
 */
public class SingletonTest {

    private SingletonTest(){

    }

    private static class LazyHolder{
        static final SingletonTest INSTANCE = new SingletonTest();
    }

    public static SingletonTest getInstance(){
        return LazyHolder.INSTANCE;
    }

    public static void main(String args[]){
        SingletonTest st=SingletonTest.getInstance();
        System.out.println(st);
    }
}
