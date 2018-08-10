package java8study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qinc
 * @version V1.0
 * @Description: 归约
 * @Date 2018/8/6 15:50
 */
public class DemoStream02 {
    public static void main(String args[]){
        Integer intArr[]={1,2,5,23,56,12,34};
        List<Integer> intList= Arrays.asList(intArr);
        //求和
        int sum=intList.stream().reduce(0,(a,b)->a+b);
        System.out.println(sum);
        //求和
        int sum1=intList.stream().reduce(0,Integer::sum);
        System.out.println(sum1);



    }
}
