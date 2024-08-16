package stack;

import java.util.Scanner;

/**
 * 栈的应用场景：
 * 方法调用栈
 * 递归算法
 * 表达式求值
 * 括号匹配
 * 深度优先搜索（DFS）
 * 二叉树的遍历
 * 数制转换
 * 在进行数制转换时，可以使用栈来存储中间的计算结果，逐步构造出目标数制的数字。
 */
//用栈完成综合计算器
//核心思路：利用运算符的优先级——
// 1.当符号栈为空时，直接入栈。
// 2.当预备加入的运算符优先级大于栈中已有的运输符，直接加入栈。
// 3.若小于，则从数字栈取出两个数，从符号栈中取出一个数，进行计算
// ——将计算后的总数加入数字栈，再将预备加入符号栈的符号加入
public class Calculate {
    public static void main(String[] args) {
        //接收字符串
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        //创建字符栈与符号栈
        ArrayStack2 numsStack = new ArrayStack2(10);
        ArrayStack2 opersStack = new ArrayStack2(10);
        //创建一个用来遍历表达式的指针
        int index = 0;
        //用于调用cal方法的三个变量
        //此处的before与after指的是出栈的先后顺序
        int before = 0;
        int after = 0;
        int oper = -1;
        char ch = ' ';//将每次扫描的结果保存至ch中
        int res = 0;
        String keepNum = "";//用于拼接多位数
        //遍历表达式
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            //如果是运算符
            if (opersStack.isOperator(ch)) {
                //判断栈的状态空OR满
                //为空，直接加入
                if (opersStack.isEmpty()) {
                    opersStack.push(ch);
                } else if (opersStack.isFull()) {
                    //满，退出循环
                    System.out.println("栈已满");
                    break;
                } else if (!opersStack.isEmpty()) {
                    //判断运算符优先级
                    if (opersStack.priority(ch) <= opersStack.priority(opersStack.peek())) {
                        before = numsStack.pop();
                        after = numsStack.pop();
                        res = numsStack.cal(before, after, opersStack.pop());
                        numsStack.push(res);
                        opersStack.push(ch);
                    } else {
                        opersStack.push(ch);
                    }
                }
            } else {
                //如果是数，就直接入栈
                //notice！记得转换
                //因为char -> int 是ASCll值。其中'1'对应49，'0'对应48
                //numsStack.push(ch - 48);

                //上面的方法仍有缺陷，无法处理多位数
                //应该为：处理数时，不能立即入栈，需判断其是否为多位数
                //index继续向后遍历一位，若为符号则入栈，否则继续向后遍历并拼接
                keepNum += ch;
                //若ch已经是最后一位字符则直接入栈
                if (index == expression.length() - 1) {
                    numsStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                } else {
                    //判断下一个字符是不是数字，如果是，就继续扫描；如果是符号，则入栈
                    //NOTICE:index不变
                    if (opersStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numsStack.push(Integer.parseInt(keepNum));
                        keepNum = "";//将keepNum清空
                    }
                }

            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //表达式已扫描完毕，开始计算栈中的值
        //当栈仅剩一个值时，该值就是结果
        while (true) {
            //当符号栈为空，则计算over
            if (opersStack.isEmpty()) {
                break;
            }
            before = numsStack.pop();
            after = numsStack.pop();
            res = numsStack.cal(before, after, opersStack.pop());
            numsStack.push(res);
        }
        System.out.printf("%s = %d", expression, numsStack.pop());
    }
}

//用数组来模拟栈的功能
class ArrayStack2 {
    private int[] value;//存放数据,数据类型由调用者来决定
    private int top = -1;//模拟栈顶的指针
    private int maxSize;//栈的大小

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        value = new int[this.maxSize];
    }

    //返回当前栈顶的值
    public int peek() {
        return value[top];
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

    //返回运算符的优先级，在该方法中数字越大，优先级越高
    public int priority(int operator) {
        if ((operator == '*') || (operator == '/')) {
            return 1;
        } else if ((operator == '+') || (operator == '-')) {
            return 0;
        } else
            //目前只支持加减乘除
            return -1;
    }

    //判断是否是运算符
    public Boolean isOperator(int operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }

    //计算
    //此处的before与after指的是出栈的先后顺序
    public int cal(int before, int after, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = before + after;
                break;
            case '-':
                res = after - before;
                break;
            case '*':
                res = after * before;
                break;
            case '/':
                res = after / before;
                break;
            default:
                break;
        }
        return res;
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