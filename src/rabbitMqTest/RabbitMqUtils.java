package rabbitMqTest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2017/8/31 11:42
 */
public class RabbitMqUtils {

    private static  Connection connection=null;
    private static Channel channel=null;
    /**
     * 获取通道
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Channel getChannel() throws IOException, TimeoutException {
        //1.创建工场
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.16.9.34");
        factory.setPort(5672);
        factory.setUsername("finance");
        factory.setPassword("ziroom1018");
        //2.获取连接,创建信道
        connection=factory.newConnection();
        channel= connection.createChannel();
        return channel;
    }

    public static void closeChannel(){
        try {
            if(channel!=null){
                channel.close();
            }
            if(connection!=null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}