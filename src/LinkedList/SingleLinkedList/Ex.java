package LinkedList.SingleLinkedList;

import java.util.Stack;

public class Ex {
    //一些练习题
    //1.求链表中的有效节点个数——通过头节点遍历
    //获取链表长度(有效节点个数)
    public int getLength(HeroNode head) {
        if (head == null) {
            return 0;//传了一个只有表头的空链表
        }
        HeroNode temp = head.next;
        int length = 0;
        if (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    //2.查找链表中的倒数第k个节点
    //思路：获取链表长度l,l-k即为目标节点的index——编写一个通过index查找节点的方法
    public HeroNode getLastIndexNode(HeroNode head, int index) {
        if (head == null) {
            return null;
        }
        int length = getLength(head);
        //检查index是否合法
        if (index <= 0 || index > length) {
            return null;
        }
        HeroNode temp = head.next;
        //遍历到目标位子
        for (int i = 0; i < length - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //3.单链表的反转
    // 思路：创建新链表，头插法
    public HeroNode reverse(HeroNode head) {
        //传入空对象OR空链表OR只有一个节点的链表皆直接返回
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        HeroNode cur = head;
        HeroNode next = null;//指向当前节点的下一节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (cur != null) {
            //遍历原来的链表，每遍历一个节点就将其取出，并将其放于新链表的最前端
            next = cur.next;//将当前节点的下一节点储存至next
            //将cur插入reverse链表的最前端(reverseHead之后，其余节点之前)
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;//让cur指针指向原先节点的下一位
        }
        head.next = reverseHead.next;
        return head;
    }

    //4.逆序打印单链表
    //思路：利用栈的特性——“先进后出”来处理
    // ps：ex3也可以采用该方法
    public void printReverse(HeroNode head) {
        if (head == null) {
            //链表不能为空
            return;
        }
        HeroNode cur = head.next;
        Stack stack = new Stack<HeroNode>();
        //将链表入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //    //5.合并两个有序链表，要求合并后的链表依然有序
//    //思路：like ex3加一个判断两个链表who的节点较小，小的先加——不必多说！直接尾接法
    public HeroNode combineNodeByOrder(HeroNode headA, HeroNode headB) {
        if (headA == null) return headB;
        if (headB == null) return headA;

        // 创建一个新的链表头
        HeroNode newHead = new HeroNode(0, "", "");
        HeroNode curA = headA.next;
        HeroNode curB = headB.next;
        HeroNode cur = newHead; // cur 指向新链表的当前尾节点

        while (curA != null && curB != null) {
            if (curA.no <= curB.no) {
                cur.next = curA;
                curA = curA.next;
            } else {
                cur.next = curB;
                curB = curB.next;
            }
            cur = cur.next;//NOTICE！
        }

        // 如果其中一个链表已经全部遍历完，直接将另一个链表剩余部分添加到新链表尾部
        if (curA != null) {
            cur.next = curA;
        } else if (curB != null) {
            cur.next = curB;
        }

        return newHead;
    }
}
//一个错误的的尝试——头接法+反转
//    public HeroNode combineNodeByOrder(HeroNode headA, HeroNode headB) {
//        //传入空对象OR空链表OR只有一个节点的链表皆直接返回
//        if (headA == null && headB == null) {
//            return null;
//        } else if (headA == null && headB != null) {
//            return headB;
//        } else if (headA != null && headB == null) {
//            return headA;
//        }
//        HeroNode curA = headA;
//        HeroNode curB = headB;
//        HeroNode nextA = null;//指向当前节点的下一节点
//        HeroNode nextB = null;
//        HeroNode cur = headA;//记录链表A与链表B中no较小的节点
//        HeroNode newHead = new HeroNode(0, "", "");
//        while (cur != null) {
//            //遍历原来的链表，每遍历一个节点就将其取出，并将其放于新链表的最前端
//            nextA = curA.next;//将当前节点的下一节点储存至next
//            nextB = curB.next;
//            //将cur插入reverse链表的最前端(reverseHead之后，其余节点之前)
//            if(nextA.no> nextB.no){
//                cur = nextB;
//                cur.next = newHead.next;
//                nextA.next =cur;
//                newHead.next =nextA;
//            }else{
//                cur =nextA;
//                cur.next = newHead.next;
//                curB.next =cur;
//                newHead.next =curB;
//            }
//            //让cur指针指向原先节点的下一位
//            cur = (nextA.no>nextB.no)?nextB:nextA;
//        }
//        printReverse(newHead);
//        return reverse(newHead);
//    }
//}
