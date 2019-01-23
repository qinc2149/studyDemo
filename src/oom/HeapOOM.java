package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinc
 * @version V1.0
 * @Description: 堆内存溢出demo
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @Date 2018/12/21 11:44
 */
public class HeapOOM {
    public static void main(String args[]){

        List<OomObject> oomList= new ArrayList<>();

        while (true){
            oomList.add(new OomObject());
        }

    }

    static class OomObject{

    }
}
