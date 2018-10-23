package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qinc
 * @version V1.0
 * @Description: 你自己实现一个本地缓存，淘汰最久未使用，你怎么设计
 * @Date 2018/10/10 17:28
 */
public class Study7 {

    List<chach> chachList=new ArrayList<>();

    public Study7() {
        try {
            del();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void set(chach c){
        chachList.add(c);
    }

    public void del() throws InterruptedException {
        for(;;){
            for(chach c:chachList){
                if(c.getTime()-System.currentTimeMillis()<=0){
                    chachList.remove(c);
                }
            }

        }
    }
    public String get(String k){
        for(chach c:chachList){
            if(c.getK().equals(k)){
                return c.getV();
            }
        }
        return null;
    }


    public static void main(String args[]){

        Study7 study7= new Study7();
        study7.set(new chach(100000,"name","qinc"));

        for (;;){
            System.out.println(study7.get("name"));
        }

    }



}



class chach{
    private int time;//有效时间
    private String k;
    private String v;

    public chach(int time, String k, String v) {
        this.time = time;
        this.k = k;
        this.v = v;
    }



    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}


