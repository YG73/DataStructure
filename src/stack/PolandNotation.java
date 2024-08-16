package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将中缀表达式转为后缀表达式的功能
        //将表达式存入ArrayList中便于遍历
        String express = "(3+4)*5-6";
        List<String> infixExpressionList = toInfixExpression(express);
        System.out.println(infixExpressionList);

        //定义逆波兰表达式
        //(3+4)*5-6 => 34+5*6- //=29
        //数字和符号之间用空格隔开
//        String suffixExpression = "3 4 + 5 * 6 -";
//        List<String> list = getList(suffixExpression);
//        System.out.printf("%s = %d", suffixExpression, calculate(list));
    }

    public static List<String> getList(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    //    //将中缀表达式转为List
    //优化版
    public static List<String> toInfixExpression(String s) {
        ArrayList<String> list = new ArrayList<String>();
        char ch;//用来保存遍历到的字符
        String str;//用来拼接多位数
        int index = 0;//指针
        do {
            ch = s.charAt(index);
            if (ch < '0' || ch > '9') {
                //运算符就直接加入list中
                list.add(ch + "");
                index++;
            } else {
                //是数，准备判断是否为多位数
                //先清空字符串str
                str = "";
                do {
                    str += ch;
                    index++;
                } while (index < s.length() && (ch = s.charAt(index)) >= '0' && ch <= '9');
            list.add(str);
            }
        } while (index < s.length());
        return list;
    }

//    public static List<String> toInfixExpression(String s) {
//        ArrayList<String> list = new ArrayList<String>();
//        char ch;//用来保存遍历到的字符
//        String str;//用来拼接多位数
//        int index = 0;//指针
//        do {
//            //如果是操作符就直接加入list中
//            if ((ch = s.charAt(index)) < '0' || (ch = s.charAt(index)) > '9') {
//                list.add(ch + "");
//                index++;
//            } else {//是数
//                //先将str清空
//                str = "";
//                //开始判断是否是多位数
//                while ((index < s.length()) && (ch = s.charAt(index)) >= '0' && (ch = s.charAt(index)) <= '9') {
//                    str += ch;
//                    index++;
//                }
//                list.add(str);
//            }
//        } while (index < s.length());
//        return list;
//    }

    //完成逆波兰计算
    //遍历list——若为数字直接加入栈；若为符号，则取出两个数，进行计算，将计算后的结果存入栈中
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            //使用正则表达式
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                //pop出来两个数
                switch (s) {
                    case "+":
                        res = num2 + num1;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num2 * num1;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }


}
