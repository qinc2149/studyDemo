package rabbitMqTest;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author qinc
 * @version V1.0
 * @Description: direct exchange 消费者
 * @Date 2017/8/31 16:16
 */
public class ConsumerMQ_Direct {
    private static  final  String DIRECT_EXCHANGE_NAME="direct_text_exchange";
    private static  final  String DIRECT_QUEUE_NAME="direct_test_queue";
    public  static void main(String args[]) throws Exception {
        //为了保险生产和消费都申明queue
        Channel channel=RabbitMqUtils.getChannel();
        //申明 direct exchange
        channel.exchangeDeclare(DIRECT_EXCHANGE_NAME, "direct");
        //申明一个队列test_queue
        channel.queueDeclare(DIRECT_QUEUE_NAME, false, false, false, null);
        //将queue绑定到exchange
        channel.queueBind(DIRECT_QUEUE_NAME,DIRECT_EXCHANGE_NAME,DIRECT_QUEUE_NAME);
        //创建消费者消费消息  push 和 pull两种模式
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("Direct Consumer Get Message : "+message);
                //手动消息确认（消息index，是否批量）
                channel.basicAck(envelope.getDeliveryTag(), false);
                //消息拒绝（消息index，是否重新放回队列）
                //channel.basicReject(envelope.getDeliveryTag(), true);

            }
        };
        channel.basicConsume(DIRECT_QUEUE_NAME,false,consumer);
        /*System.out.println(channel.basicGet(TEST_QUEUE_NAME,true).getBody().toString());*/
        //关闭连接
        //RabbitMqUtils.closeChannel();


    }
}

