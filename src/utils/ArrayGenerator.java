package utils;

import java.util.Random;

/**
 * @author pachi
 */
public class ArrayGenerator {

    private ArrayGenerator() {
    }

    public static Integer[] generateOrderedArray(int n) {

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static Integer[] generateRandomArray(int n, int bound) {

        Integer[] arr = new Integer[n];

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(bound);
        }

        return arr;
    }

    /**
     * 生成针对快速排序法标定点取中间值的特殊退化数组
     *
     * @param n 数据规模
     * @return 针对快排的特定数组
     */
    public static Integer[] generateSpecialArray(int n) {

        Integer[] ret = new Integer[n];
        generateSpecialArray(ret, 0, 0, n - 1);

        return ret;
    }

    private static void generateSpecialArray(Integer[] data, int val, int l, int r) {

        if (l > r) {
            return;
        }

        int mid = l + (r - l) / 2;
        data[l] = val;
        swap(data, l, mid);
        val++;
        generateSpecialArray(data, val, l, mid - 1);
        generateSpecialArray(data, val, mid + 1, r);
    }

    private static void swap(Integer[] data, int l, int r) {
        Integer temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }
}
