package ThreadDemo;
/**
 * @author Qinc
 * 等待线程结束（join）
 */

public class ThreadJoin {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread()+"主线程在执行开始！");
		task tk = new task();
		Thread t1= new Thread(tk);
		t1.start();
		t1.join();//表示主线程在t1线程对象上等待t1执行结束通知唤起主线程，，，，join的底层实现是调用了wait()方法
		System.out.println(Thread.currentThread()+"主线程在执行结束！");
	}
}
class task implements Runnable{

	public void run() {
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread()+"子线程在执行；");

		}

	}

}