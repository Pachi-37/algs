package sorting;

public class ShellSort {

    private ShellSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {

        int elementSpacing = data.length / 2;

        while (elementSpacing >= 1) {

            for (int start = 0; start < elementSpacing; start++) {

                // [start + elementSpacing, start + 2elementSpacing, ...]
                for (int i = start + elementSpacing; i < data.length; i += elementSpacing) {

                    E tempStore = data[i];
                    int j = 0;
                    for (j = i; j - elementSpacing >= 0 && data[j - elementSpacing].compareTo(tempStore) > 0; j -= elementSpacing) {

                        data[j] = data[j - elementSpacing];
                    }

                    data[j] = tempStore;
                }

            }
            elementSpacing /= 2;
        }
    }

    private static <E> void swap(E[] data, int l, int r) {
        E temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }
}
