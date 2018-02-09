package rabbitMqTest;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2017/9/6 17:39
 */
public class ConsumerDelayMQ {
    private static  final  String NORMAL_QUEUE_NAME="normal_text_queuq";
    private static  final  String NORMAL_EXCHANGE_NAME="normal_text_exchange";
    public  static void main(String args[]) throws Exception {
        //1，获取链接创建通道
        Channel channel=RabbitMqUtils.getChannel();
        //申明 direct exchange
        channel.exchangeDeclare(NORMAL_EXCHANGE_NAME, "direct",true);
        //声明一个正常队列
        channel.queueDeclare(NORMAL_QUEUE_NAME, false, false, false, null);
        //将queue绑定到exchange
        channel.queueBind(NORMAL_QUEUE_NAME,NORMAL_EXCHANGE_NAME,NORMAL_QUEUE_NAME);
        //创建消费者消费消息  push 和 pull两种模式
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("从正常队列中 Get Message : "+message);
            }
        };
        channel.basicConsume(NORMAL_QUEUE_NAME,true,consumer);

    }
}