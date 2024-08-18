package sorting;

import java.util.Arrays;

//希尔排序 又称之为 缩小增量排序
//--简单插入排序的plus版
//对下面做一些说明：
//arr.length/2 :取得组数和下标增量 -> 用gap变量来储存
//将循环中的每组arr[i]与arr[gap+i]进行比较，规定=> 大->小
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {-1, 43, 6, 1, 9, 33, 22, -11, 1};
        new ShellSort().shellSort_move(arr);
        System.out.println("Shell排序后的数组为：" + Arrays.toString(arr));
    }

    //Shell交换法的时间复杂度为 n^3,仅适合数据量小的情况
    public void shellSort_change(int[] arr) {
        int temp = 0;
        //此处采取 取每组尾端下标，-gap向前取得每组头下标，再向后遍历完成对该组的遍历比较
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//分组循环交换
            for (int j = gap; j < arr.length; j++) {//遍历各个组
                for (int i = j - gap; i >= 0; i -= gap) {//这里应该可以优化..
                    //此处i先指向该组的组头,以增量gap的大小进行跳跃式遍历 except：gap==1时，此处就是简单插入排序
                    if (arr[i] <= arr[i + gap]) {//满足条件！开始交换
                        temp = arr[i];
                        arr[i] = arr[i + gap];
                        arr[i + gap] = temp;
                    }
                }
            }
        }
    }

    //优化：采用Shell移动法
    public void shellSort_move(int[] arr) {
        //此处采取 取每组尾端下标，-gap向前取得每组头下标，再向后遍历完成对该组的遍历比较
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//分组循环交换
            for (int j = gap; j < arr.length; j++) {//遍历各个组
                if (arr[j] < arr[j - gap]) {//需要交换---下面其实就是插入排序
                    int i = j;//将待插入的下标与值进行保存
                    int temp = arr[j];
                    while (i >= 0 && (arr[i - gap] > temp)) {
                        arr[i] = arr[i - gap];
                        i -= gap;
                        //对上面两行代码进行说明：构思很巧妙，直接将arr[i-gap]的值覆盖在gap[i]上，从而避免temp
                    }//退出，说明已经遍历到该组组头 OR arr[i-gap]>=temp ,可以将temp直接覆盖arr[i]的值
                    arr[i] = temp;
                }
            }
        }
    }
}
