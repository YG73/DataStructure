package binary_sort_tree;

//二叉排序树
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
//        for (int i:arr
//             ) {
//            binarySortTree.add(new Node(i));
//        }
        binarySortTree.getBinarySortTree(arr);
        binarySortTree.inFixOrder();
        System.out.println("del");
        binarySortTree.delNode(10);
        binarySortTree.inFixOrder();

    }
}

class Node {
    int value;
    Node left;//左子节点的value比当前节点的value小
    Node right;//右子节点的value比当前节点的value大

    public Node(int value) {
        this.value = value;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (this.value > node.value) {//该node挂于左子节点
            if (this.left == null) {
                this.left = node;
            } else {//this.left！=null，向左递归
                this.left.add(node);
            }
        } else {//this.value <= node.value
            if (this.right == null) {
                this.right = node;
            } else {//this.right！=null，向右递归
                this.right.add(node);
            }
        }
    }

    public void inFixOrder() {
        if (this.left != null) {
            this.left.inFixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.inFixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    //查找目标节点的方法

    /**
     * @param value 需要查找的目标节点的值
     * @return 目标节点
     */
    public Node searchTargetNode(int value) {
        if (this.value == value) {
            return this;
        } else {//向子树查找
            if (this.value > value) {//向左子树递归查找
                if (this.left == null) {
                    return null;
                } else {
                    return this.left.searchTargetNode(value);
                }
            } else {//说明this.value>value,向右子树递归查找
                if (this.right == null) {
                    return null;
                } else {
                    return this.right.searchTargetNode(value);
                }
            }

        }
    }

    //需要查找的目标节点的父节点
    public Node searchPreTargetNode(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {//向子树查找
            if (this.left != null && this.value > value) {//向左子树递归查找
                return this.left.searchPreTargetNode(value);
            } else if (this.right != null && this.value <= value) {
                //说明this.value>value,向右子树递归查找
                return this.right.searchPreTargetNode(value);
            } else return null;
        }
    }
}

class BinarySortTree {
    private Node root;//根节点

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void inFixOrder() {
        if (root != null) {
            this.root.inFixOrder();
        } else System.out.println("BinarySortTree为空，无法遍历");
    }

    public void getBinarySortTree(int[] arr) {
        for (int i : arr) {
            this.add(new Node(i));
        }
    }

    //查找删除节点
    public Node searchTargetNode(int value) {
        if (root == null) return null;
        return root.searchTargetNode(value);
    }

    public Node searchPreTargetNode(int value) {
        if (root == null) return null;
        return root.searchPreTargetNode(value);
    }

    public int delRightTree(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        this.delNode(target.value);
        return target.value;
    }

    //关于二叉排序树的删除
    //有三种情况，1.删除叶子节点 2.删除有一个子树的节点 3.删除有两个子树的节点
    //针对情况2：直接将目标节点的子节点赋给目标节点的父节点指向目标节点的变量，
    // 如：preNode.left = curNode.right
    //针对情况3的解决办法：找目标节点的左子树的 max值节点 OR 找目标节点的右子树的 min值节点
    public void delNode(int value) {
        if (root == null) return;
        Node targetNode = root.searchTargetNode(value);
        if (targetNode == null) return;//找不到目标节点，直接返回
        if (root.left == null && root.right == null) {
            //该树只有一个节点，将其删除
            root = null;
            return;
        }
        Node preTargetNode = root.searchPreTargetNode(value);
        //三种情况
        //1.删除叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            //判断targetNode是preTargetNode的左子节点还是右子节点
            if (preTargetNode.right != null && preTargetNode.right.value == targetNode.value) {
                preTargetNode.right = null;
            } else if (preTargetNode.left != null && preTargetNode.left.value == targetNode.value) {
                preTargetNode.left = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            //3.删除有两个子树的节点
            //这儿采用的是找到右子树的最小值
            int minVal = delRightTree(targetNode.right);
            targetNode.value = minVal;
        } else {//2.删除只有一个子树的节点
            //该子树为左子树
            if (targetNode.left != null) {
                //判断目标节点是父节点的左节点OR右节点
                if (preTargetNode.left.value == value) {//目标节点是父节点的左节点
                    preTargetNode.left = targetNode.left;
                } else {
                    preTargetNode.right = targetNode.left;
                }
            } else {//该子树为右子树
                if (preTargetNode.left.value == value) {//目标节点是父节点的左节点
                    preTargetNode.left = targetNode.right;
                } else {
                    preTargetNode.right = targetNode.right;
                }
            }
        }
    }
//    public void delNode(int value) {
//        if (root == null) return;
//        Node targetNode = root.searchTargetNode(value);
//        if (targetNode == null) return;
//        Node preTargetNode = root.searchPreTargetNode(value);
//
//        // 修正父节点查找逻辑
//        if (preTargetNode == null) {
//            root = null; // 如果删除的是根节点且是叶子节点
//        } else {
//            if (targetNode.left == null && targetNode.right == null) {
//                // 删除叶子节点
//                if (preTargetNode.left == targetNode) {
//                    preTargetNode.left = null;
//                } else {
//                    preTargetNode.right = null;
//                }
//            } else if (targetNode.left != null && targetNode.right != null) {
//                // 删除两个子节点的节点
//                Node successor = findRightMin(targetNode);
//                targetNode.value = successor.value;
//                delNode(successor.value);
//            } else {
//                // 删除单子节点的节点
//                Node child = targetNode.left != null ? targetNode.left : targetNode.right;
//                if (preTargetNode.left == targetNode) {
//                    preTargetNode.left = child;
//                } else {
//                    preTargetNode.right = child;
//                }
//            }
//        }
//    }
//    // 查找右子树的最小节点
//    public Node findRightMin(Node node) {
//        while (node.left != null) {
//            node = node.left;
//        }
//        return node;
//    }
}