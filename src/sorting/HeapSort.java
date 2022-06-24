package sorting;

import structure.heap.MaxHeap;

public class HeapSort {

    public HeapSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {

        MaxHeap<E> maxHeap = new MaxHeap<>();

        for (int i = 0; i < data.length; i++) {
            maxHeap.add(data[i]);
        }

        for (int i = data.length - 1; i >= 0; i--) {

            data[i] = maxHeap.extractMax();
        }
    }

}
