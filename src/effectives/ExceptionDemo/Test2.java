package effectives.ExceptionDemo;

/**
 * @author qinc
 * @version V1.0
 * @Description: 对可恢复的情况使用受检异常，对编程错误使用运行时异常
 * @Date 2018/7/9 11:14
 */
public class Test2 {
    public static void main(String args[]){

     /*   int j=10/0;//编程错误--运行异常
        System.out.println(j);
*/

        int i=10;
        try {

            while (true){
                i--;
                System.out.println(i);
                if(i<0){

                        throw new Exception();

                }
            }
        } catch (Exception e) {
            i=100;
            e.printStackTrace();
        }
        System.out.println(i);



    }

}
