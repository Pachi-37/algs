package structure.heap;

import structure.Array;

public class MinHeap<E extends Comparable<E>> implements Heap<E> {

    private Array<E> data;

    public MinHeap() {
        data = new Array();
    }

    public MinHeap(int capacity) {
        data = new Array(capacity);
    }

    public MinHeap(E[] arr) {
        data = new Array<>(arr);
        if (arr.length != 1) {
            for (int i = parent(arr.length - 1); i >= 0; i--)
                shiftDown(i);
        }
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

    public E extractMin(){

        E ret = data.get(0);

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        shiftDown(0);

        return ret;
    }

    public E findMin(){

        if (data.isEmpty()) {
            throw new IllegalArgumentException("Can not find min element when heap is empty.");
        }

        return data.get(0);
    }

    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 元素上浮
    private void shiftUp(int index) {

        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) > 0) {
            data.swap(parent(index), index);
            index = parent(index);
        }
    }

    // 元素下沉
    private void shiftDown(int index) {

        while (leftChild(index) < data.getSize()) {

            int l = leftChild(index);

            if (l + 1 < data.getSize() && data.get(l).compareTo(data.get(l + 1)) > 0) {
                l = rightChild(index);
            }

            if (data.get(l).compareTo(data.get(index)) >= 0) {
                break;
            }

            data.swap(l, index);
            index = l;
        }
    }

    public E replace(E e){

        E ret = findMin();
        data.set(0, e);
        shiftDown(0);
        return ret;
    }

}
