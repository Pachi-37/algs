package sorting;

import utils.ArrayGenerator;
import utils.SortingHelper;

public class InsertionSort {

    public InsertionSort() {
    }

    /**
     * 在给定区间内实现插入排序
     * @param data
     * @param l
     * @param r
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort(E[] data, int l, int r) {

        for (int i = l; i <= r; i++) {

            for (int j = i; j - 1 >= 0 && data[j].compareTo(data[j - 1]) < 0; j--) {
                swap(data, j, j - 1);
            }
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {

        for (int i = 1; i < arr.length; i++) {

            // 将 arr[i] 插入到合适的位置
//            for (int j = i; j - 1 >= 0; j--) {
//                if (arr[j].compareTo(arr[j - 1]) < 0) {
//                    swap(arr, j, j - 1);
//                } else {
//                    break;
//                }
//            }
            for (int j = i; j - 1 >= 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    public static <E extends Comparable<E>> void sort3(E[] arr) {
        // 改变循环不变量实现插入排序

        int len = arr.length;
        for (int i = len - 2; i >= 0; i--) {
            E tmp = arr[i];

            int j;
            for (j = i; j + 1 < len && arr[j].compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = tmp;
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {

        for (int i = 0; i < arr.length; i++) {

            E tmp = arr[i];

            int j;
            for (j = i; j - 1 >= 0 && tmp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }

            arr[j] = tmp;
        }
    }

    private static <E> void insertTo(E[] arr, int order, int index) {
        E tmp = arr[index];

        if (index - order >= 0) System.arraycopy(arr, order, arr, order + 1, index - order);
        if (order + 1 < index) {
            arr[order + 1] = tmp;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
