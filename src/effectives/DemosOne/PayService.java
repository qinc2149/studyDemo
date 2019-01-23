package effectives.DemosOne;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/12/7 17:32
 */
public interface PayService {
    public void paySubmit();

    public static PayService newInstance(){
        return new WxPay();
    }

}
