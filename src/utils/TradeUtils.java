package utils;

import java.util.UUID;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/8/14 11:45
 */
public class TradeUtils {
    private TradeUtils(){}


    public static void main(String args[]){

        System.out.println(getTradeNo("801300").length());

    }

    /**
     * 长度30
     * @param companyCode
     * @return
     */
    public static String getTradeNo(String companyCode){
        String uuid=UUID.randomUUID().toString().replace("-", "");
        StringBuffer sb=new StringBuffer();
        sb.append("ZS").append(System.currentTimeMillis()).append(uuid).append(companyCode);
        return sb.toString();
    }

    /**
     * 拆单
     * @return
     */
    public static String splitBills(){
        return null;
    }

}
