package rabbitMqTest;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

/**
 * @author qinc
 * @version V1.0
 * @Description: 普通消息队列,三种exchange   direct fanout topic
 * @Date 2017/8/30 17:34
 */
public class ProducertTest {

    private static  final  String DIRECT_EXCHANGE_NAME="direct_text_exchange";
    private static  final  String DIRECT_QUEUE_NAME="direct_test_queue";
    private static  final  String FANOUT_EXCHANGE_NAME="fanout_text_exchange";
    private static  final  String FANOUT_QUEUE_NAME="fanout_text_exchange";
    private static  final  String TOPIC_EXCHANGE_NAME="topic_text_exchange";
    private static  final  String TOPIC_QUEUE_NAME="topic_text_";
    @Test
    public  void sendDirectMsg() throws IOException, TimeoutException, InterruptedException {
        //1.获取连接，创建信道
        byte[] messageBody = "Hello, rabbitMq!".getBytes();
        Channel channel=RabbitMqUtils.getChannel();
        //开启消息确认 同步confirm，还有异步confirm 异步的效率比较高
        channel.confirmSelect();
        //申明 direct exchange
        channel.exchangeDeclare(DIRECT_EXCHANGE_NAME, "direct");
        //申明一个队列test_queue
        channel.queueDeclare(DIRECT_QUEUE_NAME, false, false, false, null);
        //将queue绑定到exchange
        channel.queueBind(DIRECT_QUEUE_NAME,DIRECT_EXCHANGE_NAME,DIRECT_QUEUE_NAME);
        //发送消息
        channel.basicPublish(DIRECT_EXCHANGE_NAME, DIRECT_QUEUE_NAME, null, messageBody);
        if(channel.waitForConfirms()){
            System.out.println("消息发送成功！");
        }
        //关闭连接
        RabbitMqUtils.closeChannel();
    }
    @Test
    public void sendFanoutMsg() throws IOException, TimeoutException {
        byte[] messageBody = "Hello, fanout Exchange!".getBytes();
        //为了保险生产和消费都申明queue
        Channel channel=RabbitMqUtils.getChannel();
        //申明 fanout exchange
        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, "fanout");
        //申明n个队列test_queue
        for (int i=0;i<3;i++){
            channel.queueDeclare(FANOUT_QUEUE_NAME+i, false, false, false, null);
            //将queue绑定到exchange
            channel.queueBind(FANOUT_QUEUE_NAME+i,FANOUT_EXCHANGE_NAME,"");
        }
        //发送消息
        channel.basicPublish(FANOUT_EXCHANGE_NAME, "", null, messageBody);
        //关闭连接
        RabbitMqUtils.closeChannel();
    }
    @Test
    public void sendTopicMsg() throws IOException, TimeoutException {
        byte[] messageBody = "Hello, topic Exchange!".getBytes();
        //为了保险生产和消费都申明queue
        Channel channel=RabbitMqUtils.getChannel();
        //申明 fanout exchange
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");
        //申明n个队列test_queue
        for (int i=0;i<3;i++){
            channel.queueDeclare(TOPIC_QUEUE_NAME+i, false, false, false, null);
            //将queue绑定到exchange
            channel.queueBind(TOPIC_QUEUE_NAME+i,TOPIC_EXCHANGE_NAME,"topic_text_*");
        }
        //发送消息
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "topic_text_*", null, messageBody);

        //关闭连接
        RabbitMqUtils.closeChannel();
    }

}

