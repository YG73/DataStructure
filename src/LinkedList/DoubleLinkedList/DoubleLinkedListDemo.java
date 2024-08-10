package LinkedList.DoubleLinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }
}

class HeroNode2{
    //为方便模拟，暂且将属性设为public
    public int no;//排名
    public String name;//姓名
    public String nickName;//外号
    public HeroNode2 next;//指向下一节点！！默认为空
    public HeroNode2 pre;//指向上一节点！！默认为空


    public HeroNode2(int no, String name, String nickName) {
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

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0, "", "");
    //按添加顺序添加——即默认添加制链表尾部
    public void add(HeroNode2 heroNode) {
        //判断heroNode是否为空
        if(heroNode == null){
            System.out.println("添加对象不能为空");
            return;
        }
        //表头的节点不能移动，故需要一个新变量来储存变化的节点
        HeroNode2 temp = head;
        while (true) {
            //当temp.next为空时，说明已经遍历到链表结尾
            if (temp.next == null) {
                break;
            }
            //不是链表结尾，就到下一个节点
            temp = temp.next;
        }
        //形成双向链表
        temp.next = heroNode;
        heroNode.pre =temp;
    }

    //实现排名顺序添加
    public void addByNo(HeroNode2 heroNode) {
        HeroNode2 temp = head.next;
        Boolean noIsSame = false;//标记no是否已存在(重复),默认为false
        while (true) {
            if (temp == null) {//已经到达链表结尾,说明新添加的节点no大于任意已有节节的no
                break;
            }
            if (temp.no > heroNode.no) {//已找到目标节点的后一位！
                break;
            } else if (temp.no == heroNode.no) {//该编号已存在
                noIsSame = true;
                break;
            }
            //移动至下一节点
            temp = temp.next;
        }

        if (noIsSame == true) {
            System.out.printf("当前编号no%d已存在,请重新输入\n", temp.next.no);
        } else {
            if(temp == null){
                add(heroNode);
            }
            //插入temp前边
            heroNode.next =temp;
            heroNode.pre =temp.pre;
            temp.pre.next = heroNode;
            temp.pre = heroNode;
        }
    }

    //修改节点属性，根据no来检索目标节点
    public void update(HeroNode2 newHeroNode) {
        HeroNode2 temp = head;
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

    //双向链表可以实现自我删除，直接找到要删除的节点即可
    public void delete(HeroNode2 heroNode) {
        if (heroNode == null) {
            //throw new RuntimeException("不能删除空对象");
            System.out.println("不能删除空对象！");
            return;
        }
        HeroNode2 temp = head.next;
        Boolean nodeFound = false;
        while (true) {
            if (temp == null) {
                break;
            } else if (temp.no == heroNode.no) {
                nodeFound = true;
                break;
            }
            temp = temp.next;
        }
        if (nodeFound) {
            temp.pre.next = temp.next;
            if(temp.next!=null)
            temp.next.pre = temp.pre;
        } else {
            System.out.println("您不能删除一个不存在的节点");
        }
    }
}