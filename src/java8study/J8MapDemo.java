package java8study;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinc
 * @version V1.0
 * @Description: Map多种实用的新方法
 * @Date 2018/9/5 11:32
 */
public class J8MapDemo {
    public static void main(String args[]){
        //putIfAbsent如果key对应的v==null则插入新的v。如果不为空则返回原来的v
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        System.out.println(map.putIfAbsent(1,"222"));
        map.forEach((id, val)->{
            System.out.println("k:"+id+"   v:"+val);
        });
        //
        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);             // val33

        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33

    }
}
