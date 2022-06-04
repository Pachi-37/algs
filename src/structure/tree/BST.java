package structure.tree;

import structure.queue.ArrayQueue;
import structure.queue.Queue;
import structure.stack.ArrayStack;
import structure.stack.Stack;

public class BST<E extends Comparable<E>> {

    private class Node {

        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中插入不重复的元素
    public void add(E e) {

        root = add(root, e);
    }

    /**
     * 返回插入新界点之后二叉搜索树的根
     *
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (node.e.compareTo(e) == 0) {
            return node;
        } else if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else {
            node.right = add(node.right, e);
        }

        return node;
    }

    /**
     * 使用非递归的方法实现插入元素
     */
    public void addNonRecursive(E e) {

        // 对特殊情况进行处理
        if (root == null) {
            size++;
            root = new Node(e);
            return;
        }

        // 使用 cur 来跟踪待插入结点父节点
        Node cur = root;
        while (cur != null) {

            // 待插入值小于该结点，将其插入左子树
            if (e.compareTo(cur.e) < 0) {

                if (cur.left == null) {
                    size++;
                    cur.left = new Node(e);
                    return;
                }

                cur = cur.left;
            }
            // 待插入值大于，插入右子树
            else if (e.compareTo(cur.e) > 0) {

                if (cur.right == null) {
                    size++;
                    cur.right = new Node(e);
                    return;
                }

                cur = cur.right;
            }
            // 相等忽略
            else {
                return;
            }
        }
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {

        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return contains(node.left, e);
        }
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

        if (root == null)
            return;

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

    // 寻找二分搜索树最小元素
    public E minimum() {

        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }

        return minimum(root).e;
    }

    private Node minimum(Node node) {

        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    // 寻找二分搜索树中最大元素
    public E maximum() {
        return maximum(root).e;
    }

    private Node maximum(Node node) {

        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
    }

    // 删除最小结点
    public E removeMin() {

        E ret = minimum();
        removeMin(root);

        return ret;
    }

    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 删除最大结点
    public E removeMax() {

        E ret = maximum();
        removeMax(root);

        return ret;
    }

    private Node removeMax(Node node) {

        if (node.right == null) {

            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 删除目标结点
    public void remove(E e) {

        if (!contains(e)) {
            throw new IllegalArgumentException("BST do not exists " + e);
        }

        root = remove(root, e);
    }

    private Node remove(Node node, E e) {


        if (node == null) {
            return null;
        }

        // 找到待删除的结点
        if (node.e.compareTo(e) > 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (node.e.compareTo(e) < 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // 删除左右子树有一端为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 使用左子树的最大值或者右子树的最小值替换结点
            else {
                Node precursor = maximum(node.left);
                precursor.left = removeMax(precursor.left);

                node.left = node.right = null;
                return precursor;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private String generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return res.toString();
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);

        return res.toString();
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            res.append("--");
        }

        return res.toString();
    }
}
