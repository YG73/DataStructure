package sorting;

import java.util.Arrays;

//快速排序
//冒泡排序的plus版
//
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {32, -1, 0, 22, -9, 88};
        new QuickSort().quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序后的数组" + Arrays.toString(arr));
    }

    public void quickSort(int[] arr, int leftIndex, int rightIndex) {
        int l = leftIndex;//左下标
        int r = rightIndex;//右下标
        int pivot = arr[(l + r) / 2];//中轴的值
        int temp = 0;//临时变量
        while (l < r) {
            while (arr[l] < pivot) {//用来寻找中轴左侧元素是否有比中轴的值大的元素
                // ——若有，则直接跳出，且 l 就指向需要交换的值
                //当arr[l]>=pivot时退出
                l++;
            }
            while (arr[r] > pivot) {//用来寻找中轴右侧元素是否有比中轴的值小的元素
                r--;
            }
            if (l == r) {//说明左右两侧都满足条件，可以退出循环
                break;//
            }
            //直接交换，不用考虑，左、右侧是否指向中轴
            //若能通过if判断，则只能为以下情况：
            //case1：左右都不指向中轴，直接交换，并不断遍历直到指向中轴
            //case2：存在一侧指向中轴，则未指向中轴的一侧必然比中轴的值小 OR 比中轴的值大
            //直接交换，并将中轴的值交换前移动OR后移      妙啊！
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            //NOTICE! :case2发生交换后，可能会出现l与r均不进入while循环的情况
            //因为：l与r +=1 都需要进入while循环内，才能执行
            // 故在此处需要添加防止死循环的条件
            if (arr[r] == pivot) {
                l++;//此处用break,也是同样效果
            }
            if (arr[l] == pivot) {
                r--;//此处用break,也是同样效果
            }
        }//退出while循环，说明目前已实现了 中轴左；右侧 => All 小 ; 大
        //将左右分别有序化
        //开始递归
        if (l == r) {//将左，右指针分别移向中轴的右，左一位 => 实现一个相对效果
            l += 1;
            r -= 1;//这个+，-1还可以使得后续的递归的长度减少
            // 即使得每次迭代,r会不断逼近leftIndex！=> 使得迭代能over
        }

        if (leftIndex < r) {//向左递归，此时的r是相对于leftIndex的right下标
            quickSort(arr, leftIndex, r);
        }

        if (rightIndex > l) {
            quickSort(arr, l, rightIndex);
        }
    }
}
