package search;

/**
 * 查找目标元素的最小顶
 */
public class Upper {

    private Upper() {
    }

    public static <E extends Comparable<E>> int search(E[] data, E target) {

        int l = 0;
        int r = data.length;

        // [l, r] 查找 target
        while (r > l) {

            int mid = l + (r - l) / 2;

            if (data[mid].compareTo(target) <= 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
