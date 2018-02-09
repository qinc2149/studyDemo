package ThreadDemo;
/**
 * @author qinc2149
 * 生产者，消费者问题
 * 调用wait()，使该线程处于等待池(wait blocked pool),直到notify()/notifyAll()，
 * 线程被唤醒被放到锁定池(lock blocked pool )，释放同步锁使线程回到可运行状态（Runnable）
 */
public class ProduceAndConsumeTest {
	public static final int Max_P=4;
	public static final int Min_p=0;
	public int product=0;
	//生产
	public synchronized void produce(){
		while(true){
			if(this.product>=Max_P){
				try{
					System.out.println("产品生产满了，请稍后再生产。");
					wait();
				}catch(Exception e){
					e.printStackTrace();
				}
				//return ;
			}
			this.product++;
			System.out.println("生产者生产第" + this.product + "个产品.");
			notifyAll();   //通知等待区的消费者可以取出产品了
		}

	}
	//消费
	public synchronized void consume(){
		while(true){
			if(this.product<=Min_p){//没有商品，让出cpu时间回到Blocked状态
				try{
					System.out.println("产品没了，不可以在消费了。");
					wait();
				}catch(Exception e){
					e.printStackTrace();
				}
				//return ;

			}
			System.out.println("消费者消费了第"+product+"个产品");
			this.product--;
			notifyAll(); //通知等待区的生产者需要生产了
		}
	}
	public static void main(String[] args) {
		ProduceAndConsumeTest test= new ProduceAndConsumeTest();
		Produces produces= new Produces(test);
		Consumes consumes= new Consumes(test);
		Thread t1= new Thread(produces);
		Thread t2=new Thread(consumes);
		t1.start();
		t2.start();
	}

}
/*生产商品的线程*/
class Produces implements Runnable{
	private ProduceAndConsumeTest test=null;
	public Produces(ProduceAndConsumeTest test){
		this.test=test;
	}
	public void run() {
		test.produce();
	}
}
/*消费商品的线程*/
class Consumes implements Runnable{
	private ProduceAndConsumeTest test=null;
	public Consumes(ProduceAndConsumeTest test){
		this.test=test;
	}
	@Override
	public void run() {
		test.consume();

	}

}