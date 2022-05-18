package sorting;

public class QuickSort {

    private QuickSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {
        sort(data, 0, data.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] data, int l, int r) {

        if (r <= l) {
            return;
        }

        int spiltPoint = partition(data, l, r);
        sort(data, l, spiltPoint - 1);
        sort(data, spiltPoint + 1, r);
    }

    private static <E extends Comparable<E>> int partition(E[] data, int l, int r) {

        // 小于标定点的元素索引
        int lt = l;
        // 大于标定点元素的索引
        int gt = l;

        // [l + 1, lt] < V < [lt + 1, r]
        for (gt = l + 1; gt <= r; gt++) {

            if (data[l].compareTo(data[gt]) > 0) {
                lt++;
                swap(data, lt, gt);
            }
        }

        swap(data, l, lt);
        return lt;
    }

    private static <E> void swap(E[] data, int l, int r) {
        E temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }
}
