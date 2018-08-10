package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qinc
 * @version V1.0
 * @Description: intList2是映射后的新list，然后比较两个list有没有交叉
 * @Date 2018/8/6 21:01
 */
public class Utils2 {
    public static boolean find(Integer[] data,int sum){
        boolean flag=false;
        List<Integer> intList=Arrays.asList(data);
        List<Integer> intList2= intList.stream().map(m->{return sum-(int)m;}).collect(Collectors.toList());
        for(Integer i:intList2){
            if(intList.contains(i)){
               flag=true;
                break;
            }
        }
        return flag;
    }
    public static void main(String args[]){
        Integer[] intArr={1,2,3,1,8,9};
        System.out.println(find(intArr,8));
    }
}
