package sorting;

import java.util.Arrays;

/**
 * 归并排序 =>分+治
 * 当 left 和 right 的值满足终止条件时，递归会开始向上返回。
 * 在每一级递归返回后，调用栈中的函数会继续执行接下来的代码，即 merge 函数。
 *在每一层递归中，mergeSort 先对左边的子数组进行递归排序，然后对右边的子数组进行递归排序。
 * 当两个子数组分别递归到基本情况时，递归开始返回，merge 函数会被调用，将两个已排序的子数组合并。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {-1, 22, 3, 8, 9};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        //这里 left 和 right 分别是当前子数组的起始和结束位置。
        int mid = (left + right) / 2;
        if (left < right) {
            mergeSort(arr, left, mid, temp);//向左递归
            mergeSort(arr, mid + 1, right, temp);//向右递归
            merge(arr, left, mid, right, temp);
        }
    }


    //combine
    //
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //完成初始化，分别指向俩子序列的初始化索引
        int l = left;
        int r = mid + 1;//why？不选择用right赋值？
        int t = 0;//指向中转数组temp[]的索引

        //case1:当左右都有未加入temp的值
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {//左小
                temp[t++] = arr[l++];
            } else {//右小
                temp[t++] = arr[r++];
            }
        }

        //case2：只有单侧有剩余未加入temp[]的值
        while (l <= mid) {
            temp[t++] = arr[l++];
        }
        while (r <= right) {
            temp[t++] = arr[r++];
        }

        //将temp内的元素拷贝到arr中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }
}
