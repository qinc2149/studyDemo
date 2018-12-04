package Strategy;

/**
 * @author qinc
 * @version V1.0
 * @Description: 具体是谁买礼物送礼物的实现
 * @Date 2018/11/14 11:14
 */
public class QGivingGiftsImpl implements GivingGifts{

    @Override
    public void giving() {
        System.out.println("送礼物");
    }

    @Override
    public void buyIng() {
        System.out.println("买礼物");
    }
}
