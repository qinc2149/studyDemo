package oom;

/**
 * @author qinc
 * @version V1.0
 * @Description: 虚拟机栈内存溢出
 * -Xss128k
 * @Date 2018/12/24 18:18
 */
public class JavaVMStackSOF {

    private int stackLength=1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String args[])throws Throwable{
        JavaVMStackSOF oom= new JavaVMStackSOF();
        try {
            oom.stackLeak();
        }catch (Throwable throwable){
            System.out.println("stackLength:"+oom.stackLength);
            throw throwable;
        }

    }
}
