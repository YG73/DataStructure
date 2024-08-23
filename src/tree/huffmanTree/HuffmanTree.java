package tree.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr ={2,7,11,34,6};
        Node root = creatHuffmanTree(arr);
        root.preOrderList();
    }

    public static Node creatHuffmanTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        //先将int数组的值封装到Node对象中，在将其装入ArrayList中便于排序，增删
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        Collections.sort(nodes);//将nodes中的Node对象按照value值的大小，从小到大排序
        //开始构建哈夫曼树
        Node rigthNode,leftNode,parantNode;
        while (nodes.size() > 1) {
            //从数组中取出当前数组内的俩最小值，并将其组成一个新树后再装入原数组
            rigthNode =nodes.get(0);//令左节点的值比右节点大
            leftNode=nodes.get(1);
            parantNode = new Node(rigthNode.value+ leftNode.value);
            parantNode.left = leftNode;
            parantNode.right=rigthNode;
            //从原数组中删去取出的俩节点，并将新树加入
           nodes.remove(rigthNode);
           nodes.remove(leftNode);
           nodes.add(parantNode);
           Collections.sort(nodes);
        }
        return nodes.get(0);
    }

}

class Node implements Comparable<Node> {
    int value;//保存该节点的权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOrderList() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrderList();
        }
        if (this.right != null) {
            this.right.preOrderList();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {//小->大
        return this.value - o.value;
    }
}