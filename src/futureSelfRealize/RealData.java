package futureSelfRealize;

/**
 * @author qinc
 * @version V1.0
 * @Description: 真实的数据 构建比较耗时
 * @Date 2018/1/9 16:14
 */
public class RealData implements Data {

    private  final String result;

    public RealData(String str) {
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<9;i++){
            sb.append(str);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result=sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
