package utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by jiang on 2017/9/15.
 */
public class NumFormartUtil {


    private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖"};
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元",
            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
            "佰", "仟"};
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;


    /**
     * 税点5
     */
    private static final Integer FIVE_TAX_POINT = 5;


    /**
     * 税点7
     */
    private static final Integer SEVEN_TAX_POINT = 7;


    /**
     * 把输入的金额转换为汉语中人民币的大写
     *
     * @param numberOfMoney 输入的金额
     * @return 对应的汉语大写
     */
    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                .setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }

        return "￥"+sb.toString();
    }




    /**
     *  分转换为元 并且保留两位小数
     * @param amount
     * @return
     */
    public static String fenToYuan(Integer amount) {
        DecimalFormat df = new DecimalFormat("#0.00");
        BigDecimal bigDecimal = new BigDecimal(amount);
        return df.format(bigDecimal.divide(new BigDecimal(100)));
    }

    /**
     * 元转换为分
     * @param amount
     * @return
     */
    public static Integer changeY2F(Double amount){
        return  new BigDecimal(String.valueOf(amount)).multiply(new BigDecimal(100)).intValue();
    }

    /**
     * 计算委托代征税金的方法
     * @param invoiceAmount 租金总金额
     * @param taxPoint 税点
     * @return
     */
    public static Integer caculateWtdzTaxNum(Integer invoiceAmount,Integer taxPoint){
        BigDecimal setpOne = new BigDecimal(invoiceAmount).divide(new BigDecimal(1.05),BigDecimal.ROUND_HALF_UP,2);
        BigDecimal setpOne1 = setpOne.setScale(2, BigDecimal.ROUND_HALF_UP);
        if(FIVE_TAX_POINT == taxPoint) {
            BigDecimal setpTwo = setpOne.multiply(new BigDecimal(0.005)).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal setpTwo2 = setpTwo.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal setpThree = setpOne.multiply(new BigDecimal(0.045)).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal setpThree3 = setpThree.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal setpFoure = setpTwo2.add(setpThree3);
            BigDecimal setpFoure4 = setpFoure.setScale(0, BigDecimal.ROUND_HALF_UP);
            return setpFoure4.intValue() ;
        }if (SEVEN_TAX_POINT ==taxPoint){
            BigDecimal setpTwo = setpOne.multiply(new BigDecimal(0.01)).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal setpTwo2 = setpTwo.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal setpThree = setpOne.multiply(new BigDecimal(0.06)).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal setpThree3 = setpThree.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal setpFoure = setpTwo2.add(setpThree3);
            BigDecimal setpFoure4 = setpFoure.setScale(0, BigDecimal.ROUND_HALF_UP);
            return setpFoure4.intValue() ;
        }else {

        }
        return null;
    }

}
