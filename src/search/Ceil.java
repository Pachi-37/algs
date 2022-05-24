package search;

public class Ceil {

    private Ceil(){}

    public <E extends Comparable<E>> int ceil(E[] data, E target){

        int upper = Upper.search(data,target);

        if (upper - 1 >= 0 && data[upper - 1].compareTo(target) == 0){
            return upper - 1;
        }

        return upper;
    }
}
