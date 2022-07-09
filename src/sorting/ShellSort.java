package sorting;

public class ShellSort {

    private ShellSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {

        int elementSpacing = data.length / 2;

        while (elementSpacing >= 1) {

            for (int i = elementSpacing; i < data.length; i++) {

                E temp = data[i];
                int j = 0;
                for (j = i; j - elementSpacing >= 0 && temp.compareTo(data[j - elementSpacing]) < 0; j -= elementSpacing) {

                    data[j] = data[j - elementSpacing];
                }

                data[j] = temp;
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
