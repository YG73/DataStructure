package sorting;

import java.util.Arrays;

/**
 * 基数排序
 * 桶排序的plus
 * 通过循环比较各个位数的值的大小，按值的大小 依次装入桶（数组）中
 * 再按顺序将桶中的数重新放入arr，完成排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {10, 22, 33, 442, 12};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //首先我们需要得到arr数组中的最大位数是多少
        int maxNum = 0;//先假定一个值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxNum) {
                maxNum = arr[i];
            }
        }//退出循环，说明已找到arr中的最大值
        int maxSize = (maxNum + "").length();
        int n = 1;//用来辅助求n位的值
        int[][] bucket = new int[10][arr.length];//桶，bucket[i]是第i个桶
        int[] bucketElementCounts = new int[10];//用来记录每个桶内，储存有几个有效值
        for (int i = 0; i < maxSize; i++) {
            //遍历数组
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                //进行一下说明：位数的值和桶号相对应！
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement] += 1;
            }//说明已全部装入桶中
            //将其重新装入arr内
            //开始遍历每个桶
            int temp = 0;//用来记录arr的下标
            for (int b = 0; b < bucket.length; b++) {
                if (bucketElementCounts[b] != 0) {//说明桶不为空
                    //开始遍历这个桶
                    for (int k = 0; k < bucketElementCounts[b]; k++) {
                        arr[temp++] = bucket[b][k];
                    }//遍历完，记得将记录该桶内有效值的记录清0
                    bucketElementCounts[b] = 0;
                }
            }//所有桶均已遍历完毕，并将记录桶内有效值的记录清0
            n*=10;
        }
    }
}
