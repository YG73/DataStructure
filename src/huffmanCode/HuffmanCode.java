package huffmanCode;

import java.util.*;

/**
 * 根据赫夫曼树
 * 规定：赫夫曼编码向左路径为0，向右路径为1
 * ---赫夫曼编码是无损压缩，前缀编码
 * NOTICE:每次生产的赫夫曼树可能会不一样 <= 权值same时的排序方法不同
 * 但压缩后的长度一样
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like java do you like java";
        byte[] contentBytes = content.getBytes();
        List<Node> nodes = getNodes(contentBytes);
        Node root = creatHuffmanTree(nodes);
    }

    //将字符串转化的 byte数组 封装为 Node对象 -> ArrayList<Node>
    public static List<Node> getNodes(byte[] contentBytes) {
        //用map来记录contentBytes中的每个字符（key），及出现次数（value）
        HashMap<Byte, Integer> integerHashMap = new HashMap<Byte, Integer>();
        //将每一个byte存入map中，并记录每一个byte出现了几次
        Integer count = 0;
        for (byte key : contentBytes) {
            count = integerHashMap.get(key);//该key在contentBytes出现了几次
            if (count != null) {
                integerHashMap.put(key, count + 1);
            } else {//该key还未储存至map中
                integerHashMap.put(key, 1);
            }
        }
        ArrayList<Node> nodes = new ArrayList<>();
        //将map中的数据取出，并存入nodes
        for (Map.Entry<Byte, Integer> entry : integerHashMap.entrySet()
        ) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //用封装好的ArrayList<Node>构建一个赫夫曼树
    public static Node creatHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.value + rightNode.value);
            nodes.add(parent);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }
        return nodes.get(0);
    }

    //遍历赫夫曼树
    public void preOrder(Node root) {
        if (root == null) {
            System.out.println("树为空");
            return;
        } else
            root.preOrderList();
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 将赫夫曼编码以 Map<Byte,String> 的形式存入huffmanCodes ，如：32 -> 01
     * 用StringBuilder储存某个叶子节点的路径
     *
     * @param node 传入节点，直接传入根节点，会自动将node节点的所有叶子节点的赫夫曼编码得到，并存入
     * @param code 路径，传入的是左子节点 0 还是 右子节点 1
     * @param stringBuilder 用于拼接路径
     * @return
     */
    static HashMap<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static void getHuffmanCodes(Node node, String code, StringBuilder stringBuilder) {
        //需要对StringBuilder的内容进行操作但又不希望影响到原对象时，就可以创建一个副本进行操作
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder.append(code);
        if (node != null) {
            //判断当前节点是不是叶子节点
            if (node.data == null) {//该节点是 非叶子节点
                //递归处理
                getHuffmanCodes(node.left, "0", stringBuilder2);
                getHuffmanCodes(node.right, "1", stringBuilder2);
            } else {//该节点是 叶子节点
                //已到达某叶子节点的末尾 => 将其存入map中
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        } else {
            //node为空，则不作处理
        }
    }

    //为方便调用重载一下getHuffmanCodes的方法
    public static HashMap<Byte, String> getHuffmanCodes(Node root){
        if (root==null){
            System.out.println("树为空，无法调用getHuffmanCodes方法");
            return null;
        }
        getHuffmanCodes(root.left,"0",stringBuilder);
        getHuffmanCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    //编写一个通过赫夫曼编码将byte[]数组中存储的字符数据压缩的方法，并将压缩后的数组返回

}

class Node implements Comparable<Node> {
    Byte data;//字符
    int value;//该字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int value) {
        this.data = data;
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
                "data=" + data +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}