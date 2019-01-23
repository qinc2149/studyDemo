package oom;

import effectives.DemosOne.ParamUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinc
 * @version V1.0
 * @Description: 运行时常量池内存溢出
 * VM args：-XX:PermSize=10M     -XX:MaxPermSize=10M
 * @Date 2018/12/25 10:17
 */
public class RuntimeConstantPoolOOM {

    public static void main(String args[]){

        List<String> list= new ArrayList<>();
        int i=0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
