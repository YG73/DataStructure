package recursion;

//八皇后问题
public class Queue8 {
    int max = 8;//皇后的数量最大为8
    int[] array = new int[max];//下标代表第几行与第几个皇后；数组内部储存的是皇后所在的列的信息
    int count = 0;//用来计数，统计有多少种不同能摆放八皇后的位置

    public static void main(String[] args) {
        //耍一下！
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法", queue8.count);
    }

    //judge是用来判断皇后是否冲突的方法 ,int n指的是传入的第几个皇后
    public Boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //不能同列，和同一斜线，因其本身并不会在同一行，故无须判断是否在同一行
            if (array[i] == array[n]) {//同一列
                return false;
            } else if (Math.abs(n - i) == Math.abs(array[n] - array[i])) {//同一斜线
                return false;
            }
        }
        return true;
    }

    //放置皇后,int n指的是传入的第几个皇后
    public void check(int n) {
        if (n == 8) {//已达到八皇后！
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;//将第n皇后放入棋盘中，判断是否冲突
            if (judge(n)) {//不冲突
                check(n + 1);
            }//否则继续遍历
        }
    }

    public void print() {
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + "");
        }
        System.out.println("---法" + count + "---");
    }
}
