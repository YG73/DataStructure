package LinkedList.SingleLinkedList;

//实现将节点添加到单项链表
//Demo版——不考虑通过排名来进行排序
//思路游览：1.找到当前链表的最后节点
// 2.将该节点的next指向新节点
public class SingleLikedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(4, "mike", "d");
        HeroNode heroNode2 = new HeroNode(3, "mike", "d");
        HeroNode heroNode3 = new HeroNode(2, "mike", "d");
        HeroNode heroNode4 = new HeroNode(1, "mike", "d");

        LinkedList linkedList = new LinkedList();
        linkedList.addByNo(heroNode1);
        linkedList.addByNo(heroNode2);
        linkedList.addByNo(heroNode3);
        linkedList.addByNo(heroNode4);

        linkedList.update(new HeroNode(4, "milk", "牛奶"));

        linkedList.delete(heroNode1);

        linkedList.showList();
    }
}

//用来管理节点
class LinkedList {
    //这是表头
    private HeroNode head = new HeroNode(0, "", "");

    //按添加顺序添加
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

    //实现排名顺序添加
    //思路分析：
    // 1.找到添加的位儿的前一个节点——为确保能正常添加进去
    // 2.将新节点.next = temp.next
    // 3.原节点.next = 新节点
    public void addByNo(HeroNode heroNode) {
        HeroNode temp = head;
        Boolean noIsSame = false;//标记no是否已存在(重复),默认为false
        while (true) {
            if (temp.next == null) {//已经到达链表结尾,说明新添加的节点no大于任意已有节节的no
                break;
            }
            if (temp.next.no > heroNode.no) {//已找到目标节点的前一位！
                break;
            } else if (temp.next.no == heroNode.no) {//该编号已存在
                noIsSame = true;
                break;
            }
            //移动至下一节点
            temp = temp.next;
        }

        if (noIsSame == true) {
            System.out.printf("当前编号no%d已存在,请重新输入\n", temp.next.no);
        } else {//插入temp后边
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点属性，根据no来检索目标节点
    public void update(HeroNode newHeroNode) {
        HeroNode temp = head;
        Boolean nodeFound = false;//标记是否找到目标节点
        if (newHeroNode == null) {
            return;
        }
        while (true) {
            if (temp.no == newHeroNode.no) {
                //find succeed!
                nodeFound = true;
                break;
            } else if (temp.next == null) {
                //System.out.printf("no%d不存在,您试图添加一个不存在的节点",newHeroNode.no);
                break;//无，没有该节点——您试图添加一个不存在的节点
            } else
                temp = temp.next;
        }
        if (nodeFound == true) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("no%d不存在,您试图添加一个不存在的节点\n", newHeroNode.no);
        }
    }

    //删除
    //整体思路——addByNo的变式：
    // 1.找到想要删除的节点的前一个节点
    // 2.将前节点.next=想要删除的节点.next
    public void delete(HeroNode heroNode) {
        HeroNode temp = head;
        Boolean nodeFound = false;
        if (heroNode == null) {
            //throw new RuntimeException("不能删除空对象");
            System.out.println("不能删除空对象！");
            return;
        }
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no == heroNode.no) {
                nodeFound = true;
                break;
            }
            temp = temp.next;
        }
        if (nodeFound) {
            temp.next = temp.next.next;
        } else {
            System.out.println("您不能删除一个不存在的节点");
        }
    }

    //添加一个可直接通过no来删除节点的方法
    public void delete(int no) {
        HeroNode temp = head;
        Boolean nodeFound = false;

        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no == no) {
                nodeFound = true;
                break;
            }
            temp = temp.next;
        }
        if (nodeFound) {
            temp.next = temp.next.next;
        } else {
            System.out.println("您不能删除一个不存在的节点");
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