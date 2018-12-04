package utils;

import java.util.Arrays;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/10/26 10:56
 */
public class SortDemo2 {

    public static void main(String args[]){

        int[] intArr={20,3,89,23,46,55,6,44};
        int[] intSort=Sortmp(intArr);
        Arrays.stream(intSort).forEach(i->{
            System.out.println(i);
        });
        System.out.println(getIndex(intSort,20));



    }

    public static int[] Sortmp(int[] intArr){
        for (int i=0;i<intArr.length;i++){
            for(int j=1;j<intArr.length-i;j++){
                if(intArr[j-1]>intArr[j]){
                    int empt=intArr[j];
                    intArr[j]=intArr[j-1];
                    intArr[j-1]=empt;
                }
            }
        }
        return intArr;
    }

    //折半查找
    public static int getIndex(int[] ints, int i){
        int low=0;
        int high=ints.length-1;
        while(low <= high) {
            int middle = (low + high)/2;
            if(ints[middle]==i){
                return middle;
            }else if(i<ints[middle]) {
                high=middle-1;
            }else {
                low=middle+1;
            }
        }

        return -1;
    }

}
