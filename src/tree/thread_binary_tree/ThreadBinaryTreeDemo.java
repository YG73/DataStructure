package tree.thread_binary_tree;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadBinaryTree threadbinaryTree = new ThreadBinaryTree();
        Node root = new Node("mike", 1);
        Node node1 = new Node("jake", 2);
        Node node2 = new Node("milk", 3);
        Node node3 = new Node("pink", 4);

        threadbinaryTree.setRoot(root);
        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);

        threadbinaryTree.threadedInFixOrder();

        //threadbinaryTree.preOrder();
        //System.out.printf("node3的前驱节点：%d", node3.getLeft().getNo());
        threadbinaryTree.threadPreOrderList();

    }
}

/**
 * 线索化二叉树后，节点的左右指针有两种类型
 * 1.指向子节点or子树
 * 2.指向前驱节点，后续节点
 * 故Node需要新增用于判断左右指针类型的属性
 */

class Node {
    private String name;
    private int no;
    private Node left;
    private Node right;
    //规定：leftType =0 -> left 指向 子节点or子树。leftType = 1 -> left 指向 前驱节点
    //rightType =0 -> right 指向 子节点or子树。rightType = 1 -> right 指向 后续节点
    private int leftType = 0;
    private int rightType = 0;

    public Node(String name, int no) {
        this.name = name;
        this.no = no;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }

    //对二叉树的遍历，查找方法进行了优化，使之在线索化二叉树与二叉树皆可使用
    //优化：左右子树递归前，需要判断左右节点是否是子树
    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null && this.getLeftType() == 0) {
            this.left.preOrder();
        }
        if (this.right != null && this.getRightType() == 0) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null && this.getLeftType() == 0) {
            this.left.preOrder();
        }
        System.out.println(this);
        if (this.right != null && this.getRightType() == 0) {
            this.right.preOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null && this.getLeftType() == 0) {
            this.left.preOrder();
        }
        if (this.right != null && this.getRightType() == 0) {
            this.right.preOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public Node preOrderSearch(int no) {
        if (this.getNo() == no) {
            return this;
        }
        Node res = null;
        if (this.left != null && this.getLeftType() == 0) {
            res = this.left.preOrderSearch(no);
        }
        //判断左节点是否成功找到，若没找到才进入右节点进行查找
        if (res != null) {
            return res;
        }
        if (this.right != null && this.getRightType() == 0) {
            this.right.preOrderSearch(no);
        }
        return res;//此处已成功遍历完 子树 OR 所有节点
    }

    //中序遍历查找
    public Node inFixOrderSearch(int no) {
        //先从左节点开始查找
        Node res = null;
        if (this.left != null && this.getLeftType() == 0) {
            res = this.left.inFixOrderSearch(no);
        }
        //判断左节点是否成功找到，若没找到才进入当前节点进行查找
        if (res != null) {
            return res;
        }
        if (this.getNo() == no) {
            return this;
        }
        if (this.right != null && this.getRightType() == 0) {
            this.right.inFixOrderSearch(no);
        }
        return res;//此处已成功遍历完 子树 OR 所有节点
    }

    //后序遍历查找
    public Node postOrderSearch(int no) {
        Node res = null;
        if (this.left != null
                && this.getLeftType() == 0) {
            res = this.left.postOrderSearch(no);
        }
        //判断左节点是否成功找到，若没找到才进入右节点进行查找
        if (res != null) {
            return res;
        }
        if (this.right != null && this.getRightType() == 0) {
            this.right.postOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        //若左,右子树都没找到才进入当前节点查找
        if (this.getNo() == no) {
            return this;
        }
        return res;//此处已成功遍历完 子树 OR 所有节点
    }

    //这个del方法未进行针对 线索化二叉树 优化
    public void del(int no) {
        //从目标节点的父节点位置，执行删除目标节点的功能
        if (this.left != null && this.left.getNo() == no) {
            if (this.left.left == null && this.left.right == null) {
                this.left = null;
                return;
            }
            if ((this.left.left != null && this.left.right == null) || ((this.left.left != null && this.left.right != null))) {
                this.left = this.left.left;
                return;
            }
            if (this.left.left == null && this.left.right != null) {
                this.left = this.left.right;
                return;
            }
        }
        if (this.right != null && this.right.getNo() == no) {
            if (this.right.left == null && this.right.right == null) {
                this.right = null;
                return;
            }
            if ((this.right.left != null && this.right.right == null) || ((this.right.left != null && this.right.right != null))) {
                this.right = this.right.left;
            }
            if (this.right.left == null && this.right.right != null) {
                this.right = this.right.right;
            }
        }

        if (this.left != null) {
            this.left.del(no);
        }
        if (this.right != null) {
            this.right.del(no);
        }
    }
}

//二叉树，用来管理各个节点 => 进行优化，实现线索化
class ThreadBinaryTree {
    private Node root;
    /**
     * 因为二叉树是单向的，为实现线索化功能
     * 需新增一个属性用来保留前驱节点
     * pre总指向前一个节点
     */
    private Node pre = null;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null)
            this.root.preOrder();
    }

    public void infixOrder() {
        if (this.root != null)
            this.root.infixOrder();
    }

    public void postOrder() {
        if (this.root != null)
            this.root.postOrder();
    }

    public Node preOrderSearch(int no) {
        if (this.root == null) {
            return null;
        } else
            return this.root.preOrderSearch(no);
    }

    public Node inFixOrderSearch(int no) {
        if (this.root == null) {
            return null;
        } else
            return this.root.inFixOrderSearch(no);
    }

    public Node postOrderSearch(int no) {
        if (this.root == null) {
            return null;
        } else
            return this.root.postOrderSearch(no);
    }

    public void del(int no) {
        if (root != null) {//先判断root是否为空，不为空，则查看root是否为目标
            if (this.root.getNo() == no) {
                this.root = null;
            } else {
                this.root.del(no);
            }
        } else {
            System.out.println("这是空树");
        }
    }

    public void threadedInFixOrder() {
        this.threadedInFixOrder(root);
    }

    //编写对二叉树实现中序线索化的方法
    //传入需要线索化的节点
    //中序线索化
    public void threadedInFixOrder(Node node) {
        //先判断node是否为空
        if (node == null) {
            return;
        }
        //中序线索化
        //先线索化左子树 -> 线索化当前节点  -> 线索化右子树
        threadedInFixOrder(node.getLeft());

        //线索化当前节点
        //处理前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点 <-完成该次递归，使得pre指向上次实现前驱节点的节点，
        // 而node指向上次实现前驱节点的父节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //NOTICE! 使得pre指向下一节点的前驱节点,让pre随着node的移动而不断移动
        pre = node;

        //线索化右子树
        threadedInFixOrder(node.getRight());
    }
    //仅能在实现了线索化的二叉树中使用
    //中序遍历线索化二叉树
    public void threadInFixOrderList(){
        Node node =root;//从root开始遍历
        while (node!=null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();//不断向左子树移动
            }
            System.out.println(node);
            //利用后继结点开始遍历
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }//退出循环，说明node最后一个节点的前驱节点
            node = node.getRight();//直接替换为最后一个节点，该节点会在下次遍历完成后退出
        }
    }
    //前序
    public void threadPreOrderList(){
        Node node =root;//从root开始遍历
        while (node!=null) {
            System.out.println(node);
            while (node.getLeftType() == 0) {
                node = node.getLeft();//不断向左子树移动
                System.out.println(node);
            }
            //利用后继结点开始遍历
            while (node.getRightType() == 1) {
                node = node.getRight();
            }//退出循环，说明node最后一个节点的前驱节点
            node = node.getRight();//直接替换为最后一个节点，该节点会在下次遍历完成后退出
        }
    }
    //后序下次再说，歇会！
}