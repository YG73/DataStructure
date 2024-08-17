package sorting;

import java.util.Arrays;

//插入排序
//将一个数组分成两个part，一个是有序表，一个是无序表。
//每次遍历将无序表的第一个元素，向前与有序表的元素比大小 => 小->大
//
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {30, -1, 29, -11, 99, 6};
        System.out.println("当前数组为：" + Arrays.toString(arr));
        new InsertSort().insertSort(arr);
        System.out.println("插入排序后的数组为：" + Arrays.toString(arr));

    }

    //时间复杂度为(n+1)*n
    public void insertSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            //默认将arr的第一个元素纳入有序表中
            //暗含：i即是排序到哪个下标，也是有序表的表尾指针！
            if (arr[i] > arr[i + 1]) {//从无序表中抽取到的数较小，开始遍历有序表，找位子
                //对参数进行解释：i+1是无序表表头的下标，j是有序表标尾的下标
                for (int j = i; j >= 0; j--) {//从这儿开始是 从有序表的后往前遍历
                    if (arr[j + 1] < arr[j]) {
                        temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                    } else {//满足arr[j+1]>arr[j]
                        break;//已成功插入！
                    }
                }
            }//嗯..没什么好说的。位子已找到，就是该位置
        }
    }

    //hsp老师的方法
    public void insertSort_hsp(int[] arr) {
        if (arr.length <= 1) {
            return;//over！
        }
        //同样，将数组分成2个part.arr[0]是有序列表中的元素
        int insertVal = arr[1];//待插入的值
        int insertIndex = 0;//目标插入的下标
        int count = 0;//计数器用来记录第几次交换

        for (int i = 0; i < arr.length - 1; i++) {
            insertIndex = i;
            insertVal = arr[i + 1];
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {//待插入的值更小
                //change
                arr[insertIndex + 1] = arr[insertIndex];
                arr[insertIndex] = insertVal;
                insertIndex--;
            }//否则就成功找到位子，已成功插入
            System.out.println((++count) + "次 " + Arrays.toString(arr));
        }
    }
}
