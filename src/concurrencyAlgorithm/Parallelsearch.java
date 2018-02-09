package concurrencyAlgorithm;

import sun.security.validator.Validator;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qinc
 * @version V1.0
 * @Description: 并行查询一个很大的数组中的某一个数
 * @Date 2018/1/11 10:19
 */
public class Parallelsearch {
    public  static void main(String args[]) throws ExecutionException, InterruptedException {
         AtomicInteger result=new AtomicInteger(-1);
        int threadNum=2;
        int searchVel=78;
        int arr[]={1,23,34,781,654,781,45,32,54,783,532,3,523,78,4,983,7849,1234,78,67};
        int subArrSize=arr.length/threadNum+1;
        ExecutorService es= Executors.newFixedThreadPool(threadNum);
        ArrayList<Future<Integer>> futures= new ArrayList<>();
        for(int i=0;i<arr.length;i+=subArrSize){
            int end = i+subArrSize;
            if(end>arr.length){
                end=arr.length;
            }
            futures.add(es.submit(new searchTask(searchVel,i,end,result,arr)));
        }
        for(Future<Integer> future:futures){
            if(future.get()>0){
                System.out.println(future.get());
            }
        }
    }
}


class searchTask implements Callable{

    private int start;
    private int end;
    private int searchVel;
    private AtomicInteger result;
    private  int[] arr;
    public searchTask(int searchVel,int start,int end,AtomicInteger result,int[] arr){
        this.end=end;
        this.start=start;
        this.searchVel=searchVel;
        this.result=result;
        this.arr=arr;
    }

    public  int search(int searchVel,int start,int end){
        for(int i=start;i<end;i++){
            if(result.get()>0){//被其他线程修改了直接返回
                return result.get();
            }
            if(arr[i]==searchVel){//找到了
                if(!result.compareAndSet(-1,i)){//设置失败 表示其他线程已经找到了，直接返回
                    return result.get();
                };
            }
        }
        return -1;
    }
    @Override
    public Object call() throws Exception {
        return search(searchVel,start,end);
    }
}
