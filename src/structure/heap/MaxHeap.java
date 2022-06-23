package structure.heap;

import structure.Array;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    @Override
    public int size() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void add(E e) {
        data.addLast(e);
        shiftUp(data.getSize() - 1);
    }

    // 元素上浮
    private void shiftUp(int index) {

        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(parent(index), index);
            index = parent(index);
        }
    }

    // 寻找该元素的父亲节点
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent");
        }

        return (index - 1) / 2;
    }

    // 返回该元素的左孩子
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回该元素的右孩子
    private int rightChild(int index) {
        return index * 2 + 2;
    }
}
