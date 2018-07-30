package effectives.ExceptionDemo;

/**
 * @author qinc
 * @version V1.0
 * @Description: 只针对异常的情况才使用异常，尽量不要利用异常去做一些其他的东西，比如下面的代码
 * @Date 2018/7/3 11:35
 */
public class Test1 {

    public static void main(String args[]) {

        int[] munArr = {1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 9, 0, 1, 21, 4, 35, 46, 576};

        //1，标准方法
        for (int i : munArr) {
            System.out.println("标准方法" + i);
        }

        //2.自以为是的高效方法
        /**
         * 缺点：1，语意不明确。2，效率不一定高，因为很少有jvm对异常流程做优化，
         * 还有try catch 中的代码jvm有些优化不生效，而且标准方式一直再被优化性能不差
         * 3，容易产生错误的结果，如果try中别的方法抛个同样的异常，那么程序就错误的任务遍历结束了，其实数据还没有遍历完成
         */
        int i = 0;
        while (true) {
            try {
                System.out.println("自以为是的高效方法" + munArr[i++]);
                arrayEx();
            } catch (ArrayIndexOutOfBoundsException ae) {
                break;
            }
        }

    }
    public static  void arrayEx(){
        int j[]={};
        System.out.println(j[10]);
    }
}
