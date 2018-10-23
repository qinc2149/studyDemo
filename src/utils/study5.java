package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinc
 * @version V1.0
 * @Description: 从一个数组里找重复出现次数最多的一个数
 * @Date 2018/10/10 16:40
 */
public class Study5 {

    public static void main(String args[]){
        int[] ints={1,3,13,34,45,2,3,56,5,13,13,13};

        Map<Integer,Integer> countMaps= new HashMap<>();
        for (Integer i:ints){
            if(countMaps.get(i)==null){
                countMaps.put(i,1);
            }else{
                countMaps.put(i,(countMaps.get(i)+1));
            }
        }

        countMaps.forEach((id, val)->{
            System.out.println("k:"+id+"   v:"+val);
        });




    }
}
