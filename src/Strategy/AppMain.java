package Strategy;

/**
 * @author qinc
 * @version V1.0
 * @Description: 模拟客户端,客户端看不到具体的算法
 * @Date 2018/2/22 11:42
 */
public class AppMain {

    public static void main(String args[]){
        String type="满减";
        Context context= new Context(type);
        context.getRestlt();
    }
}
