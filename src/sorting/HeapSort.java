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

    public static <E extends Comparable<E>> void sortHeapify(E[] data) {

        if (data.length <= 1) {
            return;
        }

        for (int i = (data.length - 2) / 2; i >= 0; i--) {
            shiftDown(data, i, data.length);
        }

        for (int i = data.length - 1; i >= 0; i--) {

            swap(data, 0, i);
            shiftDown(data, 0, i);
        }
    }


    /**
     * 下沉操作
     *
     * @param data  数组
     * @param index 下沉索引
     * @param count 最大堆范围 data[0, count)
     * @param <E>
     */
    private static <E extends Comparable<E>> void shiftDown(E[] data, int index, int count) {

        while (2 * index + 1 < count) {

            int j = 2 * index + 1;

            if (j + 1 < count && data[j + 1].compareTo(data[j]) > 0) {
                j++;
            }

            if (data[j].compareTo(data[index]) <= 0) {
                break;
            }

            swap(data, j, index);
            index = j;
        }
    }

    private static <E> void swap(E[] data, int l, int r) {
        E temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }
}
