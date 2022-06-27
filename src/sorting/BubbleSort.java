package sorting;

public class BubbleSort {

    private BubbleSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {

        for (int i = 0; i < data.length - 1; i++) {

            boolean isSwapped = false;
            for (int j = 0; j < data.length - 1 - i; j++) {

                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j + 1);
                    isSwapped = true;
                }
            }

            if(!isSwapped) {
                break;
            }
        }
    }

    private static <E> void swap(E[] data, int l, int r) {
        E temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }
}
