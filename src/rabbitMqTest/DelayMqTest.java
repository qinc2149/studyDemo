package rabbitMqTest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author qinc
 * @version V1.0
 * @Description: 延迟消息队列
 * @Date 2017/9/6 16:08
 */
public class DelayMqTest {
    private static  final  String NORMAL_EXCHANGE_NAME="normal_text_exchange";
    private static  final  String NORMAL_QUEUE_NAME="normal_text_queuq";
    private static  final  String DELAY_EXCHANGE_NAME="delay_text_exchange";
    private static  final  String DELAY_QUEUE_NAME="delay_text_queuq";
    /**
     *Queue TTL 给队列设置过期时间 x-message-ttl
     *Per-Message TTL  给某条消息设置过期时间。
     * 如果给消息和队列同时设置过期时间，则以最小的那个为准
     */
    @Test
    public void sendDelayMsg() throws IOException, TimeoutException {
        byte[] messageBody = "有效期短的消息在队尾！60".getBytes();
        //1.获取连接，创建信道
        Channel channel=RabbitMqUtils.getChannel();
        //申明 正常的exchange
        channel.exchangeDeclare(NORMAL_EXCHANGE_NAME, "direct",true);
        channel.queueDeclare(NORMAL_QUEUE_NAME, false, false, false, null);
        channel.queueBind(NORMAL_QUEUE_NAME,NORMAL_EXCHANGE_NAME,NORMAL_QUEUE_NAME);
        //申明 delay exchange
        channel.exchangeDeclare(DELAY_EXCHANGE_NAME, "direct",true);
        Map<String, Object> arg  = new HashMap<String, Object>();
        arg.put("x-dead-letter-exchange", NORMAL_EXCHANGE_NAME);
        arg.put("x-dead-letter-routing-key", NORMAL_QUEUE_NAME);
        //创建一个队列，队列的消息过期时间为60秒
       // arg.put("x-message-ttl", 60000);
        arg.put("x-max-priority", 10);
        channel.queueDeclare(DELAY_QUEUE_NAME, false, false, false, arg);
        //将queue绑定到exchange
        channel.queueBind(DELAY_QUEUE_NAME,DELAY_EXCHANGE_NAME,DELAY_QUEUE_NAME);

        //发送消息，将该消息的过期时间，设置为2秒
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.priority(1000000000);
        builder.expiration("60000").deliveryMode(2);
        AMQP.BasicProperties properties = builder.build();
        channel.basicPublish(DELAY_EXCHANGE_NAME, DELAY_QUEUE_NAME, properties, messageBody);
        //关闭连接
        RabbitMqUtils.closeChannel();
    }


}




