package search;

/**
 * 在一个有序数组中查找下界
 */
public class Lower {

    private Lower() {
    }

    public static <E extends Comparable<E>> int lower(E[] data, E target) {

        int l = -1;
        int r = data.length - 1;

        // target >=
        while (r > l) {

            int mid = l + (r - l + 1) / 2;
            if (data[mid].compareTo(target) < 0) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }

    public static <E extends Comparable<E>> int lowerFloor(E[] data, E target) {

        int lower = lower(data,target);

        // 如果数组中不存在元素，返回 lower
        if (lower + 1 < data.length && data[lower].compareTo(target) != 0){
            return lower;
        } else {
            return lower + 1;
        }

    }
}
