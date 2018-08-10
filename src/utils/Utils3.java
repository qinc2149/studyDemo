package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qinc
 * @version V1.0
 * @Description: 合成一个list，排序+截取
 * @Date 2018/8/6 21:29
 */
public class Utils3 {

    public static List<Long> topn(Long[][] array, int k){
        List<Long> loList=new ArrayList();
        for(int i=0;i<array.length;i++){
           List<Long> lList= Arrays.asList(array[i]);
            loList.addAll(lList);
        }
        Collections.sort(loList);
        Collections.reverse(loList);
        return loList.subList(0,k);
    }

    public static void main(String args[]){
        Long[] intArr={1L,2L,3L};
        Long[] intArr1={4L,6L,5L};
        Long[][] array={intArr,intArr1};
        System.out.println(topn(array,3));
    }
}
