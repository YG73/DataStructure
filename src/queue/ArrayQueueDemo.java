package queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //可在此尽情测试！
        ArrayQueue arrayQueue = new ArrayQueue(4);
        char key =' ';//接收用户输入
        Boolean loop =true;//是否显示菜单
        Scanner scanner = new Scanner(System.in);
        //菜单
        while(loop){
            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 退出队列");
            System.out.println("a(add) 添加队列");
            System.out.println("g(get) 从队列中取出数据");
            System.out.println("h(head) 显示队列头的数据");
            System.out.print("\n请输入您的选择：");
            key = scanner.next().charAt(0);
            switch (key){
                case 's' :
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    System.out.println("程序退出");
                    break;
                case 'a':
                    System.out.print("\n请输入您想增加的值：");
                    int value =scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据为：%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("队列头的数据为："+arrayQueue.showHead());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}

//编写一个用数组模拟队列的类
class ArrayQueue {
    private int maxSize;//表示该数组的最大容量
    private int real;//队列尾
    private int front;//队列头
    private int arr[];//该数组用于模拟队列，储存数据

    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        real = -1;//只想队列尾，即指向队列尾的数据
        front = -1;//指向队列头，front是指向队列头的前一个位置
    }

    //判断队列是否为空
    public Boolean isEmpty() {
        return real == front;
    }

    //判断队列是否满
    public Boolean isFull() {
        return real == maxSize - 1;
    }

    //获得队列数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列为空");
        }
        front++;//front后移
        return arr[front];
    }

    //添加数据到队列
    public void addQueue(int i) {
        //判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满，不能添加！");
            return;
        }
        real++;//让real后移
        arr[real] = i;
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空！无法显示数据！");
            return;
        }
        //遍历
        for (int i = 0; i < arr.length ; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //展示队列头部数据，注意不是取出数据！
    public int showHead(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];
        //System.out.printf("%d\n",arr[front+1]);
    }
}
