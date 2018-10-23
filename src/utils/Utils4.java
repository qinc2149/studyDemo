package utils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author qinc
 * @version V1.0
 * @Description: aabc 和caba相等
 * @Date 2018/8/10 15:01
 */
public class Utils4 {
    public static void main(String args[]){
        System.out.println(Integer.parseInt("123"));

       // System.out.println(eqStr("aabc","caba"));
    }


    public static boolean eqStr(String s1,String s2){
        String a=Arrays.stream(s1.split("")).sorted().collect(Collectors.joining());
        String b=Arrays.stream(s2.split("")).sorted().collect(Collectors.joining());
        return a.equals(b);
    }
}
