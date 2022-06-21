package structure.map;

import structure.tree.BST;

import java.security.Key;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node right, left;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            right = null;
            left = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {

        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {

        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (node.key.compareTo(key) == 0) {
            node.value = value;
            return node;
        } else if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else {
            node.right = add(node.right, key, value);
        }

        return node;
    }

    @Override
    public V remove(K key) {

        Node node = getNode(root, key);

        if (node != null) {
            root = remove(root,key);
            return node.value;
        }

        return null;
    }

    private Node remove(Node node, K key) {


        if (node == null) {
            return null;
        }

        // 找到待删除的结点
        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
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
                precursor.right = node.right;

                node.left = node.right = null;
                return precursor;
            }
        }
    }

    private Node maximum(Node node) {

        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
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


    @Override
    public boolean contains(K key) {

        Node node = getNode(root, key);
        return node != null;
    }

    @Override
    public V get(K key) {

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {

        Node node = getNode(root, key);

        if (node != null) {
            node.value = value;
        } else {
            throw new IllegalArgumentException(key + " does not exists.");
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 返回以 node 为根节点的二分搜索树，key 所在的节点
    private Node getNode(Node node, K key) {

        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }
}
