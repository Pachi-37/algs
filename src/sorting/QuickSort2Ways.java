package sorting;

import utils.RandomNumberGenerator;

public class QuickSort2Ways {

    private QuickSort2Ways() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {
        sort(data, 0, data.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] data, int l, int r) {

        if (r - l <= 15) {
            InsertionSort.sort(data, l, r);
            return;
        }

        // 随机化标定点
        int p = RandomNumberGenerator.generate(l, r);
        swap(data, p, l);

        int spiltPoint = partition(data, l, r);
        sort(data, l, spiltPoint - 1);
        sort(data, spiltPoint + 1, r);
    }

    private static <E extends Comparable<E>> int partition(E[] data, int l, int r) {

        int i = l + 1;
        int j = r;

        while (true) {
            while (i <= j && data[i].compareTo(data[l]) < 0) {
                i++;
            }

            while (j >= i && data[j].compareTo(data[l]) > 0) {
                j--;
            }

            if (i >= j) {
                break;
            }

            swap(data, i, j);
            i++;
            j--;
        }

        swap(data, l, j);
        return j;
    }

    private static <E> void swap(E[] data, int l, int r) {
        E temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }
}
