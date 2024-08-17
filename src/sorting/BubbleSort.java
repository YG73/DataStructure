package sorting;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

//冒泡排序
//时间复杂度为 T(n)=n^2
//每次比较两个数，将大数移动至后边
//每次循环将最大数移动至最后：小->大
//总共会比较arr.length!次;循环arr.length-1次
//完成了排序的优化
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        int temp = 0;
        boolean flag = false;//标识符：标识是否有进行交换操作，默认为false ->用来优化冒泡排序
        // ——预期效果为：当某次循环中没有交换的操作时，认为其已实现有序——则将flag设为false
        System.out.println("排序前的数组为：" + Arrays.toString(arr));

//        Date date = new Date();//这个是用来计算耗时的
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//        String data1Str = simpleDateFormat.format(date);
//        System.out.println("排序前的时间为："+data1Str);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < (arr.length - 1 - i); j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag == false) {
                break;
            } else {
                flag = false;//重置flag
            }
        }
//        Date date2 = new Date();//这个是用来计算耗时的
//        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//        String data2Str = simpleDateFormat2.format(date2);
//        System.out.println("排序后的时间为："+data2Str);

        System.out.println("排序后的数组为：" + Arrays.toString(arr));
    }

    public void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;//标识符：标识是否有进行交换操作，默认为false ->用来优化冒泡排序
        // ——预期效果为：当某次循环中没有交换的操作时，认为其已实现有序——则将flag设为false
        //System.out.println("排序前的数组为：" + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < (arr.length - 1 - i); j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag == false) {
                break;
            } else {
                flag = false;//重置flag
            }
        }
        //System.out.println("排序后的数组为：" + Arrays.toString(arr));
    }
}
