package search;

public class Ceil {

    private Ceil() {
    }

    public <E extends Comparable<E>> int ceil(E[] data, E target) {

        int upper = Upper.search(data, target);

        if (upper - 1 >= 0 && data[upper - 1].compareTo(target) == 0) {
            return upper - 1;
        }

        return upper;
    }

    /**
     * 寻找 >= target 的最小索引
     *
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> int lowerCeil(E[] data, E target) {

        int l = 0;
        int r = data.length;

        while (r > l) {

            int mid = l + (r - l) / 2;

            if (data[mid].compareTo(target) < 0){
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
