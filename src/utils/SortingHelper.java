package utils;

import sorting.InsertionSort;
import sorting.SelectionSort;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author pachi
 * @date 2021-6-13
 */
public class SortingHelper {

    private SortingHelper() {
    }

    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String sortName, E[] arr) {

        try {

            Class sortClass = Class.forName("sorting." + sortName);
            Method sortMethod = sortClass.getMethod("sort", Comparable[].class);
            Object[] params = new Object[]{arr};

            long startTime = System.nanoTime();

            sortMethod.invoke(null, params);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            if (!SortingHelper.isSorted(arr)) {
                throw new RuntimeException(sortName + " failed");
            }

            System.out.println(String.format("%s , n = %d : %f s", sortName, arr.length, time));
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
