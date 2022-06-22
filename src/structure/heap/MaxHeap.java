package structure.heap;

import structure.Array;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
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

    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index-0 does not have parent");
        }

        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }
}
