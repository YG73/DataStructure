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
        System.out.println("中缀表达式："+infixExpressionList);
        //定义逆波兰表达式
        //(3+4)*5-6 => 34+5*6- //=29
        //数字和符号之间用空格隔开----------
//        String suffixExpression = "3 4 + 5 * 6 -";
//        List<String> list = getList(suffixExpression);
        //将得到的中缀表达式转换为后缀表达式储存至List中
        List<String> list = parseSuffixExpression(infixExpressionList);
        System.out.println("后缀表达式："+list);
        System.out.printf("%s = %d", express, calculate(list));
    }

    public static List<String> parseSuffixExpression(List<String> list) {
        //通常会创建两个栈，一个符号栈和一个储存中间结果的栈
        //但因为储存中间结果的栈，只有进，没有出。
        // 所以可以选择使用ArrayList来储存，可以避免使用栈储存还需反转的问题
        Stack<String> s1 = new Stack<>();//符号栈
        ArrayList<String> s2 = new ArrayList<>();
        for (String item : list) {
            //如果是数,直接将数加入栈中
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {//如果是”（“，加入s1中
                s1.push(item);
            } else if (item.equals(")")) {//如果是”）“，依次弹出s1中的的符号，并压入s2中，直到遇见”（“
                while (s1.size() > 0 && (!s1.peek().equals("("))) {
                    s2.add(s1.pop());
                }//然后记得将”（“弹出，完成一个括号内的计算
                s1.pop();
            } else {//这儿就是对运算符进行处理
                //规定：+ -的优先级为1，* / 的优先级为2
                //编写一个判断优先级的方法
                //若后加入的运算符小于先前就有的运输符，则将s1原先的运算符弹出，压入s2
                while (s1.size() > 0 && (priority(s1.peek()) >= priority(item))) {
                    s2.add(s1.pop());
                }
                //别忘了将后加入的运算符入栈
                s1.push(item);
            }
        }
        //将s1中的剩余符号全部取出，并放入s2
        while (s1.size() > 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static int priority(String s) {
        int res = 0;
        switch (s) {
            case "+":
            case "-":
                res = 1;
                break;
            case "*":
            case "/":
                res = 2;
                break;
            default:
                System.out.println("运算符有误！请检查！");
                break;
        }
        return res;
    }

    public static List<String> getList(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    //将中缀的List转换为后缀的List

    //    //将中缀表达式转为List
    //做了一些优化
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
