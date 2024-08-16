package stack;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.omg.CORBA.Object;

public class ArrayStackDemo {

}

//用数组来模拟栈的功能
class ArrayStack {
    private int[] value;//存放数据,数据类型由调用者来决定
    private int top = -1;//模拟栈顶的指针
    private int maxSize;//栈的大小

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        value = new int[this.maxSize];
    }

    //判断栈满
    public Boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈是否为空
    public Boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("当前栈已满");
            return;
        }
        top++;
        this.value[top] = value;
        return;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("当前栈为空，无法出栈");
        }
        int temp = this.value[top];
        top--;
        return temp;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("当前栈为空");
            return;
        }
        int i = top;
        for (; i > 0; i--) {
            System.out.printf("value[%d]：%d\n", i, value[i]);
        }
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}