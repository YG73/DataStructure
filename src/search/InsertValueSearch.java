package search;

/**
 * 插值查找
 * 实际上是二分查找的升级版
 * 将二分查找中关于mid变量的 int mid = (left + right) / 2 = left + (right - left) / 2;
 * 将后边乘以1/2的系数改为(key-arr[left])/(arr[right]-arr[left])
 * ---此处减去的是left的值，是因为其小
 * 因为无论是插值还是二分法查找，都是在有序数组中查找
 * 但插值查找因自适应 => 在数据量大且数据分布稠密的数组来说，计算量小
 * 但不适用于数组元素值相差大的情况
 */
public class InsertValueSearch {
    public static void main(String[] args) {

    }

    //会返回目标值的下标索引，若无，则返回-1
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        //findValue < arr[left] || findValue > arr[right] 还可以在防止在处理大量数据时，
        // 因为mid的计算而导致越界
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
            //数组中没有目标值
            return -1;
        }
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        if (findValue < arr[mid]) {//向左递归
            return insertValueSearch(arr, left, mid - 1, findValue);
            //此处的采用mid-1而非mid，1.减少数据量 2.避免无限递归
        } else if (findValue > arr[mid]) {//向右d递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else {//成功找到
            return mid;
        }
    }
}
