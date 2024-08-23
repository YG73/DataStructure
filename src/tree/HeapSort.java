package tree;

import java.util.Arrays;

//堆排序算法使用的是完全二叉树结构
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
//第一次调整 => {4,9,8,5,6}
//        adjustHeap(arr, arr.length / 2 - 1, arr.length);
//        System.out.println("第一次调整"+ Arrays.toString(arr));
//        adjustHeap(arr, arr.length / 2 - 1-1, arr.length);
//        System.out.println("第二次调整"+ Arrays.toString(arr));//9，6，8，5，4
//==>规律：因为从左到右，从下到上，故从last非叶子节点开始，循环-1 --> 遍历所有非叶子节点
        heapSortBig(arr);
        System.out.println("堆排序后" + Arrays.toString(arr));//9，6，8，5，4

    }

    /**
     * 将数组调整为大顶堆
     *
     * @param arr         待调整数组
     * @param notLeafNode 非叶子节点在数组的索引
     * @param length      表示需要对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int notLeafNode, int length) {
        int temp = arr[notLeafNode];//将非叶子节点的值储存于temp中
        //从左到右，从下到上
        for (int k = notLeafNode * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//比较左右子节点的大小
                k++;//说明右节点比较大
            }
            if (arr[k] > temp) {//若子节点比父节点大，将子节点赋予父节点
                arr[notLeafNode] = arr[k];
                notLeafNode = k;//并将指向非叶子节点在数组的索引移动
            } else {//父节点大，不做任何处理
                //因为从左到右，从下到上 -> 该非叶子节点(为last[未比较过的]非叶子节点)
                // 与子节点全部比较完毕 =>退出循环比较
                break;
            }
        }//退出for循环
        //处理两种情况
        // 1.若子节点比父节点的值大，这此处的notLeafNode指向k（比父节点的值大的子节点索引）
        // 2.父节点大 => 无事发生
        arr[notLeafNode] = temp;
    }

    //对循环进行封装
    public static void heapSortBig(int[] arr) {
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }//数组已调整为大顶堆
        //开始交换
        //将堆顶元素与末尾元素交换
        int temp =0;//用来储存末尾元素
        for (int j = arr.length-1 ;j >0 ; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //重新调整数组结构，使之满足大堆顶
            adjustHeap(arr,0,j);//此时大数已沉至数组底部，末尾的数被提至堆顶
            //在循环中，每次循环的末尾的数将会先与根节点的左右子节点比较大小，并不断循环...
        }
    }

}
