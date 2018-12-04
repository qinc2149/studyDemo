package utils;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/10/26 10:56
 */
public class SortDemo {

    public static void main(String args[]){

        int[] intArr={20,3,89,23,46,55,6,44};

        //冒泡排序

        for (int i=0; i<intArr.length; i++){
            for(int j=1;j<intArr.length-i;j++){
                if(intArr[j-1]>intArr[j]){
                    int temp = intArr[j-1];
                    intArr[j-1] = intArr[j];
                    intArr[j] = temp;
                }

            }


        }

        for(int item: intArr){
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println("----------------");
        System.out.println( binarySearch(intArr,20));

    }

   //二分查询算法
    public static int binarySearch(int[] arr, int x) {
         int low = 0;
         int high = arr.length-1;
         while(low <= high) {
             int middle = (low + high)/2;
             if(x == arr[middle]) {
                 return middle;
              }else if(x <arr[middle]) {
                  high = middle - 1;
              }else {
                low = middle + 1;
             }
         }
         return -1;
     }


}
