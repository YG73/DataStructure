package search;

import java.util.ArrayList;

//二分法查找
//NOTICE!必须为有序数组cai才可以使用二分法查找
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 4, 7, 22, 33};
        int findValue = 22;
        int orderIndex = binarySearch(arr, 0, arr.length-1, findValue);
        System.out.println(findValue + "在数组的下标为" + orderIndex);
    }

    //会返回目标值的下标索引，若无，则返回-1
    public static int binarySearch(int[] arr, int left, int right, int findValue) {
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
            //数组中没有目标值
            return -1;
        }
        int mid = (left + right) / 2;
        if (findValue < arr[mid]) {//向左递归
            return binarySearch(arr, left, mid - 1, findValue);
            //此处的采用mid-1而非mid，1.减少数据量 2.避免无限递归
        } else if (findValue > arr[mid]) {//向右d递归
            return binarySearch(arr, mid + 1, right, findValue);
        } else {//成功找到
            return mid;
        }
    }

    //对二分法查找的升级版
    //当一个数组内有多个目标值时，能够返回一个下标集合
    //对 return mid; =>  先向mid左，右递归，确认无了。再返回
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findValue) {

        if (left > right) {
            //数组中没有目标值
            return null;
        }
        int mid = (left + right) / 2;
        if (findValue < arr[mid]) {//向左递归
            return binarySearch2(arr, left, mid - 1, findValue);
            //此处的采用mid-1而非mid，1.减少数据量 2.避免无限递归
        } else if (findValue > arr[mid]) {//向右d递归
            return binarySearch2(arr, mid + 1, right, findValue);
        } else {//成功找到
            ArrayList<Integer> integers = new ArrayList<>();
            //因为arr是有序的，故重复的值必定在边上。采取直接扫描的方法
            int temp;
            while (true) {
                temp = mid - 1;//向左扫描
                if ((arr[temp] != findValue) || temp < 0) {
                    break;
                } else {
                    integers.add(temp);
                    temp--;
                }
            }
            integers.add(mid);//将中间的加入
            while (true) {
                temp = mid + 1;//向右扫描
                if ((arr[temp] != findValue) || temp > arr.length - 1) {
                    break;
                } else {
                    integers.add(temp);
                    temp++;
                }
            }
            return integers;
        }
    }
}
