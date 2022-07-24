package structure.segmentTree;

public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {

        this.merger = merger;
        data = (E[]) new Object[arr.length];

        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {

        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {

        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 根据业务逻辑来决定
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    private int leftChild(int parent) {
        return parent * 2 + 1;
    }

    private int rightChild(int parent) {
        return parent * 2 + 2;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();

        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");

        return res.toString();
    }
}
