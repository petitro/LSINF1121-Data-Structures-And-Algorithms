//question 1: [2,4],[5,9],[10,10]
public class Union {

    public static Interval merge(Interval interval1, Interval interval2){
        int min;
        if(interval1.getMin()<interval2.getMin()) min = interval1.getMin();
        else min=interval2.getMin();

        int max;
        if(interval1.getMax()>interval2.getMax()) max=interval1.getMax();
        else max=interval2.getMax();

        return new Interval(min, max);
    }

    private static void sort(Interval[]a, Interval lo, Interval hi) {
        if (lo.compareTo(hi) < 0) {return} //lo.min is lower than hi.min
        //lo.min is not lower than hi.min
        int j = partition(a, lo, hi);
        // ! devrait etre Interval
        sort(a, lo, j-1); // Sort left part a[lo .. j-1].
        sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // Partition (see page 291).
        sort(a, lo, j-1); // Sort left part a[lo .. j-1].
        sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
    }

    private static void exch(Interval[] a, Interval interval1, Interval interval2){

    }

    private static int partition(Interval[] a, Interval lo, Interval hi){
        // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo.getMin(), j = hi.getMin()+1; // left and right scan indicess
        Interval v = a[lo.getMin()]; // partitioning item
        while (true)
        { // Scan right, scan left, check for scan complete, and exchange.
            while (a[++i].compareTo(v)<0) if (i == hi.getMin()) break; //++i === i=i+1
            while (a[--j].compareTo(v)<0) if (j == lo.getMin()) break; //--j === j=j-1
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }

    private static int partition(Comparable[] a, int lo, int hi)
    { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices
        Comparable v = a[lo]; // partitioning item
        while (true)
        { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }


    public static Interval [] union(Interval [] intervals) {
        Interval [] intervalsUnion;
        for(int i=0; i<intervals.length-1; i++){
            if (Interval[i].getMin()<Interval[i+1])
        }

        return new Interval[]{};
    }
}