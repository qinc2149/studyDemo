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
         Integer amount = null;
       // System.out.println(Integer.parseInt("123"));
        int tabIndex=getTabIndex(4,"10.16.25.67");
        System.out.println(amount.intValue());



    }


    public static boolean eqStr(String s1,String s2){
        String a=Arrays.stream(s1.split("")).sorted().collect(Collectors.joining());
        String b=Arrays.stream(s2.split("")).sorted().collect(Collectors.joining());
        return a.equals(b);
    }


    /**
     *
     * @param h
     * @param key
     * @return
     */
    public static int getTabIndex(int h, String key){
        int hash = (key.hashCode() ^ (key.hashCode() >>> h)) & 0x7fffffff;
        int tabIndex=(h-1) & hash;
        return tabIndex;
    }
}
