package LinkedList.DoubleLinkedList;

/**
 * 约瑟夫问题OR约瑟夫环
 * 有n个人围成一圈，从某个人开始报数，每次数到m的人会被淘汰出圈，然后从下一个人开始继续报数，
 * 直到最后只剩下一个人，问题是要找出最后剩下的这个人是最初编号为多少的人OR求出出队编号的序列
 * 思路：环形链表OR环形数组
 * 解法：
 * 1.不带头节点的单向环形链表
 * 2.
 */

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.creatCircleSingleLinkedList(5);
        circleSingleLinkedList.show();
    }

}

//一个Boy表示一个节点
class Boy {
    private int no;
    private Boy next;//指向下一个节点
//private String name属性可有可无

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

//单项环形链表
class CircleSingleLinkedList {
    private Boy first = null;//创建一个指向头节点的指针，当前无节点

    public void creatCircleSingleLinkedList(int size) {
        if (size < 1) {
            System.out.println("链表长度不能<1");
            return;
        }
        Boy curBoy = null;//辅助指针，用来构建环形链表——该指针总是指向no最大的节点

        for (int i = 1; i <= size; i++) {
            //根据编号创造节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                //形成环状
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }

    }

    public void show() {
        if (first == null) {
            System.out.println("链表为空，无法打印");
        }
        Boy boy = first;//用来遍历链表
        while (true) {
            System.out.printf("是编号为%d的Boy\n", boy.getNo());
            if (boy.getNext() == first) {
                break;
            }
            boy = boy.getNext();
        }
    }

    public void countBoys(int startNo, int countNums, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数设置有误");
            return;
        }
        Boy helper = first;//指向first后一位节点，用来帮助遍历
        //先将first移动至startNo的节点
        while (true) {
            if (first.getNo() == startNo) {
                break;
            }
            first = first.getNext();
        }
        //将helper移动至first指向的节点的前一位
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //因为报数是从自己开始，所以实际上helper与first只需移动 countNums-1 个节点
        while (true) {
            if(helper == first){
                //说明圈内只有一个节点嘞，over
                break;
            }
            for (int i = 0; i <= (countNums - 1); i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时first所指向的节点，出圈
            System.out.printf("编号为%d的Boy，出圈\n",first.getNo());
            helper.setNext(first.getNext());
            first = first.getNext();
        }
        System.out.printf("编号为%d的Boy，出圈\n",first.getNo());
    }
}