package Strategy;

/**
 * @author qinc
 * @version V1.0
 * @Description: 静态代理 只能代理一个人
 * @Date 2018/11/14 11:16
 */
public class ProxyGiving implements GivingGifts  {

    QGivingGiftsImpl qGivingGifts;
    public ProxyGiving(){
        qGivingGifts=new QGivingGiftsImpl();
    }
    @Override
    public void giving() {
        qGivingGifts.giving();
    }

    @Override
    public void buyIng() {
        qGivingGifts.buyIng();
    }
}
