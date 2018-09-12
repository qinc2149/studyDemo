package ThreadDemo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author qinc
 * @version V1.0
 * @Description: 分而治之 fork/Join框架
 * ForkJoin 把一个大任务分为多个子任务
 * @Date 2018/1/2 16:17
 */
public class CountTask extends RecursiveTask{

        private static final long THRESHOLD=50;
        private long start;
        private long end;
        public CountTask(long start,long end){
            this.start=start;
            this.end =end;
        }

        @Override
        protected Object compute() {
            long sum=0;
            boolean isCompute=(end-start)<THRESHOLD;
            //任务足够小或者不可分
            if(isCompute){
                for(long i=start;i<=end;i++){
                    sum+=i;
                }
            }else{
                long setp=(end+start)/5;
                ArrayList<CountTask> tasks= new ArrayList<>();
                long pos=start;
                for(int i=0;i<5;i++){
                    long lastOne=pos+setp;
                    if(lastOne>=end) {
                        lastOne=end;
                    }
                    CountTask subTask=new CountTask(pos,lastOne);
                    pos+=setp+1;
                    tasks.add(subTask);
                    subTask.fork();
                }
                for(CountTask task:tasks){
                    sum+=(long)task.join();
                }
            }
            return sum;
        }

    public static void main(String args[]){
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        CountTask task= new CountTask(0,100L);
        ForkJoinTask<Long> result=forkJoinPool.submit(task);
        try {
            long res=(long)result.get();
            System.out.println("sum:"+res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
