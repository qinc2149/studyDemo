package ThreadDemo;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author qinc
 * @version V1.0
 * @Description: 管道流，主要用于线程之间传输数据
 * @Date 2018/2/24 9:41
 */
public class PipedDemo {

    public static void main(String args[]) throws IOException {

        //写出管道流
        PipedWriter out=new PipedWriter();
        //读入管道流
        PipedReader in = new PipedReader();
        //将out与in绑定。不然会抛出异常
        out.connect(in);
        //此线程向out中写
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int receive = 0;
                    while ((receive = System.in.read()) != -1) {
                        out.write(receive);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //此线程读
        new Thread(new Runnable() {
            @Override
            public void run() {
                int receive = 0;
                try {
                    while ((receive = in.read()) != -1) {
                        System.out.print((char) receive);
                    }
                } catch (IOException ex) {
                }
            }
        }).start();





        }

}
