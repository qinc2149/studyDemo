package rabbitMqTest;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author qinc
 * @version V1.0
 * @Description: topic exchange 消费者
 * @Date 2017/8/31 16:16
 */
public class ConsumerMQ_Topic {
    private static  final  String TOPIC_EXCHANGE_NAME="topic_text_exchange";
    private static  final  String TOPIC_QUEUE_NAME="topic_text_";
    public  static void main(String args[]) throws Exception {
        //为了保险生产和消费都申明queue
        Channel channel=RabbitMqUtils.getChannel();
        //申明 topic exchange
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");
        //申明3个队列并且绑定大 fanout exchange
        for (int i=0;i<3;i++){
            channel.queueDeclare(TOPIC_QUEUE_NAME+i, false, false, false, null);
            //将queue绑定到exchange
            channel.queueBind(TOPIC_QUEUE_NAME+i,TOPIC_EXCHANGE_NAME,"topic_text_*");
        }
        //创建消费者消费消息  push 和 pull两种模式
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("Topic Consumer Get Message : "+message);
            }
        };
        for (int i=0;i<3;i++){
            channel.basicConsume(TOPIC_QUEUE_NAME+i,true,consumer);
        }

        /*System.out.println(channel.basicGet(TEST_QUEUE_NAME,true).getBody().toString());*/
        //关闭连接
        //RabbitMqUtils.closeChannel();


    }
}




