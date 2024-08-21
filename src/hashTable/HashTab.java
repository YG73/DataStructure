package hashTable;

public class HashTab {
}

//节点，表示一个雇员
class Emp {
    private int id;
    private String name;
    private Emp next;//指向下一个节点

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //表示一条链表
    class HashLinkedList {
        private Emp head;

        //添加节点至链表
        public void add(Emp emp) {
            if (this.head == null) {
                this.head = emp;
                return;
            }
            //若添加的不是第一个雇员
            Emp index = head;//用来记录遍历到哪一个雇员
//            while (index.getNext() != null) {
//                index = index.getNext();
//            }//退出循环，说明index.next为空
//            index.setNext(emp);
            //实现根据id大小添加至链表中
            while (true) {
                if (index.next == null) {
                    index.setNext(emp);
                    return;
                }
                if (index.next.getId() > emp.id) {
                    Emp nextTemp = index.next;
                    index.setNext(emp);
                    emp.setNext(nextTemp);
                    return;
                }
                index = index.next;
            }
        }

        //遍历链表节点
        public void list(int no) {
            if (this.head == null) {
                System.out.println((no + 1) + "链表为空");
                return;
            }
            Emp curIndex = head;
            while (curIndex != null) {
                System.out.printf("->第%d条链表：id=%d - name=%s\t", no + 1, curIndex.getId(), curIndex.getName());
                curIndex = curIndex.next;
            }
        }

        public void del(int id) {
            if (head == null) {
                return;
            }
            //找到我们要删除的雇员的前一个
            Emp curEmp = head.next;
            Emp preEmp = head;
            if (this.head.getId() == id) {
                this.head = preEmp.next;
                return;
            }
            while (curEmp != null && curEmp.getId() != id) {
                preEmp = preEmp.next;
                curEmp = curEmp.next;
            }
            if (curEmp != null) {
                preEmp.setNext(curEmp.next);
            }
        }
    }

    //用来管理链表的数组--NOTICE:操作时，用户直接操作的是该表
    class HashTable {
        private HashLinkedList[] hashLinkedLists;
        int size;

        //初始化->确认有几个链表
        public HashTable(int size) {
            this.size = size;
            hashLinkedLists = new HashLinkedList[size];
            //将每一条链表填充至数组中
            for (int i = 0; i < size; i++) {
                hashLinkedLists[i] = new HashLinkedList();
            }
        }

        //编写散列函数
        public int hashFun(int id) {
            return id % size;//取模法
        }

        //添加雇员
        public void add(Emp emp) {
            //根据散列函数，获得员工应该放置的位置
            int linkedListNo = hashFun(emp.getId());
            hashLinkedLists[linkedListNo].add(emp);
        }

        //遍历所有链表
        public void list() {
            for (int i = 0; i < size; i++) {
                hashLinkedLists[i].list(i);
            }
        }

        //根据id查找雇员
        public Emp findEmpById(int id) {
            int linkedListNo = hashFun(id);
            Emp curEmp = hashLinkedLists[linkedListNo].head;
            while (curEmp != null) {
                if (curEmp.getId() == id) {
                    return curEmp;
                }
                curEmp = curEmp.next;
            }
            return null;
        }

        //根据id删除某个雇员
        public void delEmpById(int id) {
            int linkedListNo = hashFun(id);
            hashLinkedLists[linkedListNo].del(id);
        }
    }
}