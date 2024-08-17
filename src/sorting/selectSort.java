package sorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//选择排序
//时间复杂度仍然为T(n)=n^2
//共有arr.length-1次循环,最后剩余的元素不必进行排序
//每次循环都会遍历一次数组中剩余的未排序的元素，并找出其中最小的元素，交换位置——>小->大
public class selectSort {
    public static void main(String[] args) {
        int[] arr = {23, -1, -22, 3};
        System.out.println("当前数组为：" + Arrays.toString(arr));

//        Date date1 = new Date();//这个是用来计算耗时的
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//        String data1Str = simpleDateFormat.format(date1);
//        System.out.println("排序前的时间为："+data1Str);

        int small = 0;//用来当前遍历的保存最小值
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j < arr.length - 1; j++) {
                small = arr[j];//假设开始遍历的值是剩余未遍历元素中最小的数
                if (arr[j] > arr[j + 1]) {
                    //此处也可以添加一个flag来判断是否存在更小值
                    small = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = small;
                }
            }
        }
//        Date date2 = new Date();//这个是用来计算耗时的
//        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//        String data2Str = simpleDateFormat2.format(date2);
//        System.out.println("排序后的时间为："+data2Str);

        System.out.println("选择排序后的数组为：" + Arrays.toString(arr));

    }
}
