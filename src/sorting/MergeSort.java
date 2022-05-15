package sorting;

import java.util.Arrays;

public class MergeSort {

    private MergeSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {

        E[] temp = Arrays.copyOf(data, data.length);

        sort(data, 0, data.length - 1, temp);
    }

    private static <E extends Comparable<E>> void sort(E[] data, int l, int r, E[] temp) {

        if (r - l <= 20) {
            InsertionSort.sort(data, l, r);
            return;
        }
        int mid = l + (r - l) / 2;

        sort(data, l, mid, temp);
        sort(data, mid + 1, r, temp);

        // 代码优化
        if (data[mid].compareTo(data[mid + 1]) > 0) {
            merge(data, l, mid, r, temp);
        }

    }

    /**
     * 合并两个有序的区间
     *
     * @param data
     * @param l
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void merge(E[] data, int l, int mid, int r, E[] temp) {

        // 将数组中元素拷贝公共数组中
        System.arraycopy(data, l, temp, l, r - l);

        int i = l;
        int j = mid + 1;

        for (int k = l; k <= r; k++) {

            if (i > mid) {
                data[k] = temp[j];
                i++;
            } else if (j > r) {
                data[k] = temp[i];
                j++;
            } else if (temp[i].compareTo(temp[j]) < 0) {
                data[k] = temp[i];
                i++;
            } else {
                data[k] = temp[j];
                j++;
            }
        }
    }
}
