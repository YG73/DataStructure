package tree;

//测试前序，中序，后序遍历 => 区别在于 输出当前当前节点的代码位于
// 递归左节点，递归右节点的代码 前中后位置
//前序，中序，后序查找与遍历的方法基本一致，将输出改为比较
public class HeroNode {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node root = new Node("mike", 1);
        Node node1 = new Node("jake", 2);
        Node node2 = new Node("milk", 3);
        Node node3 = new Node("pink", 4);

        binaryTree.setRoot(root);
        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);

        //binaryTree.infixOrder();
        //该树仅用来测试 1234  2134    2431

        //System.out.println(binaryTree.inFixOrderSearch(15));

    }
}

class Node {
    private String name;
    private int no;
    private Node left;
    private Node right;

    public Node(String name, int no) {
        this.name = name;
        this.no = no;
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

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.preOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
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
        if (this.left != null) {
            res = this.left.preOrderSearch(no);
        }
        //判断左节点是否成功找到，若没找到才进入右节点进行查找
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            this.right.preOrderSearch(no);
        }
        return res;//此处已成功遍历完 子树 OR 所有节点
    }

    //中序遍历查找
    public Node inFixOrderSearch(int no) {
        //先从左节点开始查找
        Node res = null;
        if (this.left != null) {
            res = this.left.inFixOrderSearch(no);
        }
        //判断左节点是否成功找到，若没找到才进入当前节点进行查找
        if (res != null) {
            return res;
        }
        if (this.getNo() == no) {
            return this;
        }
        if (this.right != null) {
            this.right.inFixOrderSearch(no);
        }
        return res;//此处已成功遍历完 子树 OR 所有节点
    }

    //后序遍历查找
    public Node postOrderSearch(int no) {
        Node res = null;
        if (this.left != null) {
            res = this.left.postOrderSearch(no);
        }
        //判断左节点是否成功找到，若没找到才进入右节点进行查找
        if (res != null) {
            return res;
        }
        if (this.right != null) {
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

//二叉树，用来管理各个节点
class BinaryTree {
    private Node root;

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
}