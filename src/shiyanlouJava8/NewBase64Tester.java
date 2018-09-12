package shiyanlouJava8;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author qinc
 * @version V1.0
 * @Description:
 *              在Java 8中，内置了Base64编解码相关的特性。我们可以在Java 8中使用下面三种类型的Base64编解码：
简易模式：输出是完全按照A-Za-z0-9+/字符集映射的。编码不会自己增加输出行，解码器也不会接受任何超出A-Za-z0-9+/范围的内容。
URL模式：输出基于A-Za-z0-9+/的映射，但对于URL和文件名是安全的。
MIME模式：输出对于MIME类型的内容是友好的。如果超过76个字符，则会换行输出，并且换行符\n之后会自动添加一个\r。如果某行没有\r则说明输出的内容已经结束。

Base64相关的内部类有：
Base64.Encoder：这是一个静态类。实现了Base64的编码功能，格式遵循了RFC 4648和RFC 2045标准。
Base64.Decoder：也是一个静态类。实现了Base64的解码功能。
相关的方法有：
getEncoder()：该方法返回一个使用基本Base64编码格式的Encoder对象。相反的解码方法是getDecoder()。
getUrlEncoder()：该方法返回一个使用URL类型的Base64编码格式的Encoder对象。相反的解码方法是getUrlDecoder()。
getMimeEncoder()：该方法返回一个使用MIME类型的Base64编码格式的Encoder对象。相反的解码方法是getMimeDecoder()
 * @Date 2018/8/22 11:41
 */
public class NewBase64Tester {
    public static void main(String args[]){
        try {

            // 使用基本的Base64编码
            String base64encodedString = Base64.getEncoder().encodeToString("Shiyanlou.com".getBytes("utf-8"));
            System.out.println("Basic base64 encoding:" + base64encodedString);

            // 解码并输出结果
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println("Original content: " + new String(base64decodedBytes, "utf-8"));

            // 使用URL类型的Base64编码
            base64encodedString = Base64.getUrlEncoder().encodeToString("Shiyanlou.com".getBytes("utf-8"));
            System.out.println("URL base64 encoding:" + base64encodedString);

            // MIME类型的Base64编码
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("MIME base64 encoding:" + mimeEncodedString);

        }catch(UnsupportedEncodingException e){
            // 捕获异常并输出
            System.out.println("Exception:" + e.getMessage());
        }
    }
}
