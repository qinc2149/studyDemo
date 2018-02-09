package ThreadDemo;

import java.util.concurrent.*;

/**
 * @author Qinc
 * cancel方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false。参数mayInterruptIfRunning表示是否允许取消正在执行却没有执行完毕的任务，如果设置true，则表示可以取消正在执行过程中的任务。如果任务已经完成，则无论mayInterruptIfRunning为true还是false，此方法肯定返回false，即如果取消已经完成的任务会返回false；如果任务正在执行，若mayInterruptIfRunning设置为true，则返回true，若mayInterruptIfRunning设置为false，则返回false；如果任务还没有执行，则无论mayInterruptIfRunning为true还是false，肯定返回true。
 * isCancelled方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。
 * isDone方法表示任务是否已经完成，若任务完成，则返回true；
 * get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
 * get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。
 */
public class CallableAndFuture {
	public static void main(String[] args) {
		myCallable task1= new myCallable(0);
		myCallable task2= new myCallable(1);
		myCallable task3= new myCallable(2);
		FutureTask<String> futureTask= new FutureTask(new myCallable(0));
		ExecutorService es = Executors.newFixedThreadPool(3);
		Future future=es.submit(task1);
		Future future2=es.submit(task2);
		Future future3=es.submit(task3);
		es.submit(futureTask);
		try {
			String str=(String) future.get();
			System.out.println(str);
			System.out.println("任务是否已经完成:"+future.isDone());

			future2.cancel(true);

			System.out.println("任务是否已经被取消了："+future2.isCancelled());
			System.out.println(futureTask.get());
			future3.get();



		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("task3抛出异常了");

		}
		es.shutdownNow();

	}

}

/*任务类
 * Callable接口是concurrent包下面的一个接口和Runnable接口类似，实现Callable接口的类和实现Runnable的类都是可被其他线程执行的任务
 * 区别是：
 * 	·Callable接口中的call方法有返回值，Runnable接口的run方法是没有返回值的
 * 	·call方法可以抛出异常，run方法是不可以抛出异常的
 * 	·那么怎么使用Callable呢？一般情况下是配合ExecutorService来使用的
 * */
class myCallable implements Callable{
	private int flag;
	public myCallable(int flag){
		this.flag=flag;
	}
	@Override
	public String call() throws Exception {
		if(flag==0){
			return "call方法可以有返回值";
		}else if(flag==1){
			while(true){
				Thread.sleep(2000);
				System.out.println("looping");

			}
		}else{
			throw new Exception("call方法还可以抛出异常");
		}
	}

}