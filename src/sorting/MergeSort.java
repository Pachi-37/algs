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
     * 自底向上实现归并排序
     *
     * @param data
     * @param <E>
     */
    public static <E extends Comparable<E>> void sortBU(E[] data) {

        E[] temp = Arrays.copyOf(data, data.length);
        sortBU(data, 0, data.length, temp);
    }

    private static <E extends Comparable<E>> void sortBU(E[] data, int l, int r, E[] temp) {

        // 遍历合并区间
        for (int sz = 1; sz < r; sz += sz) {
            // 合并 [i, i + sz - 1] 和 [i + sz, Min(i + sz + sz - 1, r)]
            for (int i = l; i + sz < r; i += sz + sz) {

                if (data[i + sz - 1].compareTo(data[i + sz]) > 0) {
                    merge(data, i, i + sz - 1, Math.min(i + 2 * sz - 1, r - 1), temp);
                }
            }
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
        System.arraycopy(data, l, temp, l, r - l + 1);

        int i = l;
        int j = mid + 1;

        for (int k = l; k <= r; k++) {

            if (i > mid) {
                data[k] = temp[j];
                j++;
            } else if (j > r) {
                data[k] = temp[i];
                i++;
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
