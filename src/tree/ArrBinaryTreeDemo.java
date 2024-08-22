package tree;

/**
 * 数组和二叉树可相互转换
 * -------------
 * 在数组中，第n个元素的左节点：2*n+1;右节点：2*n+2
 * 父节点：(n-1)/2
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 8, 10};
    }
}

//实现顺序存储二叉树
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }
    public void inFixOrder() {
        this.inFixOrder(0);
    }
    public void postOrder() {
        this.postOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.println(arr[index]);
        //向左递归
        if (index * 2 + 1 < arr.length) {
            preOrder(index * 2 + 1);
        }
        //向右递归
        if (index * 2 + 2 < arr.length) {
            preOrder(index * 2 + 2);
        }
    }
    public void inFixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        //向左递归
        if (index * 2 + 1 < arr.length) {
            inFixOrder(index * 2 + 1);
        }
        System.out.println(arr[index]);
        //向右递归
        if (index * 2 + 2 < arr.length) {
            inFixOrder(index * 2 + 2);
        }
    }
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        //向左递归
        if (index * 2 + 1 < arr.length) {
            postOrder(index * 2 + 1);
        }
        //向右递归
        if (index * 2 + 2 < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.println(arr[index]);System.out.println(arr[index]);
    }
}