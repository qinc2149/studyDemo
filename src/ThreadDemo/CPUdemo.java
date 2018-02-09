package ThreadDemo;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/1/30 10:07
 */
public class CPUdemo {
    public static void main (String args[]){
        for(int i=0;i<30;i++){

            new Thread(new Runnable(){
                int j=0;
                @Override
                public void run() {
                    //while(true){
                            j++;
                       //}



                }
            }).start();
       /* int j=0;
        while(true){
            j++;
        }*/




    }}
}
