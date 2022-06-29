package sorting;

public class BubbleSort {

    private BubbleSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {

        for (int i = 0; i < data.length - 1; ) {

            int lastSwappedIndex = 0;
            for (int j = 0; j < data.length - 1 - i; j++) {

                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j + 1);
                    lastSwappedIndex = j + 1;
                }
            }

//            if (lastSwappedIndex == 0) {
//                break;
//            }

            i = data.length - lastSwappedIndex;
        }
    }

    private static <E> void swap(E[] data, int l, int r) {
        E temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }
}
