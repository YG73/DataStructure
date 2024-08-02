package LinkedList.SingleLinkedList;

//实现将节点添加到单项链表
//Demo版——不考虑通过排名来进行排序
//思路游览：1.找到当前链表的最后节点
// 2.将该节点的next指向新节点
public class SingleLikedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "mike", "d");
        HeroNode heroNode2 = new HeroNode(2, "mike", "d");
        HeroNode heroNode3 = new HeroNode(3, "mike", "d");
        HeroNode heroNode4 = new HeroNode(4, "mike", "d");

        LinkedList linkedList = new LinkedList();
        linkedList.add(heroNode1);
        linkedList.add(heroNode2);
        linkedList.add(heroNode3);
        linkedList.add(heroNode4);

        linkedList.showList();
    }
}

//用来管理节点
class LinkedList {
    //这是表头
    private HeroNode head = new HeroNode(0, "", "");

    public void add(HeroNode heroNode) {
        //表头的节点不能移动，故需要一个新变量来储存变化的节点
        HeroNode temp = head;
        while (true) {
            //当temp.next为空时，说明已经遍历到链表结尾
            if (temp.next == null) {
                temp.next = heroNode;
                break;
            }
            //不是链表结尾，就到下一个节点
            temp = temp.next;
        }
    }

    //展示链表储存的内容
    public void showList() {
        //不需要展示表头，故从head.next开始遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("链表为空咯~");
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//这是节点
class HeroNode {
    //为方便模拟，暂且将属性设为public
    public int no;//排名
    public String name;//姓名
    public String nickName;//外号
    public HeroNode next;//指向下一节点！！

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}