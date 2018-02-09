package casDemo;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;


import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qinc
 * @version V1.0
 * @Description: Disruptor:无锁的缓存框架
 * @Date 2018/1/9 11:10
 */
public class DisruptorDemo {
    public static void  main(String args[]) throws InterruptedException {
        ExecutorService ex= Executors.newCachedThreadPool();
        PCdataFactory pCdataFactory=new PCdataFactory();
        int bufferSize=1024;
        Disruptor<PCdata> disruptor= new Disruptor<PCdata>(pCdataFactory,
                bufferSize,
                ex,
                ProducerType.MULTI,
                new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(new Consumer(),new Consumer(),new Consumer(),new Consumer());
        disruptor.start();
        RingBuffer<PCdata> ringBuffer=disruptor.getRingBuffer();
        Producer producer=new Producer(ringBuffer);
        ByteBuffer bb=ByteBuffer.allocate(8);
        for (long i=0;true;i++){
            bb.putLong(0,i);
            producer.pushData(bb);
            Thread.sleep(100);
            System.out.println("add:"+i);
        }


    }

}

class Producer {
    private final RingBuffer<PCdata> ringBuffer;
    public Producer(RingBuffer<PCdata> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    public void pushData(ByteBuffer bb){
        long sequence=ringBuffer.next();
        PCdata event=ringBuffer.get(sequence);
        event.setValue(bb.getLong(0));
        ringBuffer.publish(sequence);
    }

}

/**
 * PCdata工厂类
 */
class PCdataFactory implements EventFactory<PCdata>{
    @Override
    public PCdata newInstance() {
        return new PCdata();
    }
}

/**
 * 消费者
 */
class Consumer implements WorkHandler<PCdata>{

    @Override
    public void onEvent(PCdata pCdata) throws Exception {

        System.out.println("线程:"+Thread.currentThread().getId()+"消费"+pCdata.getValue());
    }
}

/**
 * 数据实体
 */
class PCdata{
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}