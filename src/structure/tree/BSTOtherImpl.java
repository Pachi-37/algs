package structure.tree;

import structure.queue.ArrayQueue;
import structure.queue.Queue;
import structure.stack.ArrayStack;
import structure.stack.Stack;

public class BSTOtherImpl<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;
        public int sz;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
            sz = 1;
        }
    }

    private Node root;
    private int size;

    public BSTOtherImpl() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    private int size(Node node) {
        return node == null ? 0 : node.sz;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        node.sz = size(node.right) + size(node.left) + 1;
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {

        if (node == null)
            return false;

        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else // e.compareTo(node.e) > 0
            return contains(node.right, e);
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {

        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 非递归实现前序遍历
    public void preOrderNR() {

        if (root == null) {
            return;
        }

        Stack<Node> stack = new ArrayStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }

    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {

        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 层序遍历
    public void levelOrder() {

        if (root == null) {
            throw new IllegalArgumentException("BST is empty.");
        }

        Queue<Node> queue = new ArrayQueue<>();

        queue.enqueue(root);

        while (!queue.isEmpty()) {

            Node cur = queue.dequeue();

            System.out.println(cur.e);

            if (cur.left != null) {
                queue.enqueue(cur.left);
            }

            if (cur.right != null) {
                queue.enqueue(cur.right);
            }
        }
    }

    // 寻找二分搜索树的最小元素
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");

        return minimum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        node.sz = size(node.left) + size(node.right) + 1;
        return node;
    }

    // 从二分搜索树中删除最大值所在节点
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        node.sz = size(node.left) + size(node.right) + 1;
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    private Node remove(Node node, E e) {

        if (node == null)
            return null;

        Node retNode = null;
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            retNode = node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            retNode = node;
        } else {   // e.compareTo(node.e) == 0

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }

            // 待删除节点右子树为空的情况
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        node.sz = size(node.left) + size(node.right) + 1;
        return retNode;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

    // 返回元素所处的的位置
    public int rank(E e) {

        if (!contains(e)) {
            return -1;
        }

        return rank(root, e);
    }

    private int rank(Node node, E e) {

        if (node.e.compareTo(e) == 0) {
            return size(node.left) + 1;
        } else if (node.e.compareTo(e) > 0) {
            return rank(node.left, e);
        }

        return size(node.left) + 1 + rank(node.right, e);
    }

    // 查找位置为 index 的元素(0 <= index <= size)
    public E select(int index) {

        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("index is out of range.");
        }

        return select(root, index);
    }

    private E select(Node node, int index) {

        if (index < size(node.left)) {
            return select(node.left, index);
        } else if (index == size(node.left)) {
            return node.e;
        } else {
            return select(node.right, index - size(node.left) - 1);
        }
    }
}
