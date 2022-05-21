package sorting;

import utils.RandomNumberGenerator;

public class QuickSort3Ways {

    private QuickSort3Ways() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {
        sort(data, 0, data.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] data, int l, int r) {

        if (l >= r) {
            return;
        }

        int p = RandomNumberGenerator.generate(l, r);
        int lt = l, gt = r + 1;
        swap(data, l, p);

        for (p = l + 1; p < gt; p++) {

            if (data[l].compareTo(data[p]) > 0) {
                lt++;
                swap(data, p, lt);
            } else if (data[l].compareTo(data[p]) < 0) {
                gt--;
                swap(data, p, gt);
                p--;
            }
        }

        swap(data, l, lt);
        sort(data, l, lt - 1);
        sort(data, gt, r);
    }

    private static <E extends Comparable<E>> void swap(E[] data, int l, int r) {
        E temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }
}
