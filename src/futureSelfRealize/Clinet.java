package futureSelfRealize;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/1/9 16:37
 */
public class Clinet {

    public Data request(String reqStr){
        FutureData futureData=new FutureData();
        new Thread(){//开启一个线程构建RealData
            @Override
            public void run(){
                RealData realData = new RealData(reqStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }

    public static void main(String args[]){
        Clinet clinet = new Clinet();
        Data data=clinet.request("qinc");
        System.out.println("处理一些业务逻辑！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("业务逻辑处理完成");
        System.out.println("realData:"+data.getResult());
        System.out.println("结束");
    }
}
