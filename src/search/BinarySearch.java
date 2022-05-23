package search;

public class BinarySearch {

    private BinarySearch() {
    }

    public static <E extends Comparable<E>> int search(E[] data, E target) {

        int l = 0;
        int r = data.length - 1;
        int mid = l + (r - l) / 2;

        while (r >= l) {

            mid = l + (r - l) / 2;

            if (data[mid].compareTo(target) == 0) {
                return mid;
            } else if (data[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

}
