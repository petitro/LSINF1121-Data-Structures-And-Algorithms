
/*
meme algorithme que la definition de merge sort!!!
 */

public class MergeSort {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        //copie a dans aux
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) { //left is smaller
                a[k] = aux[j++];
            } else if (j > hi) { //right is bigger
                a[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) { //right is smaller
                a[k] = aux[j++];
            } else { //left is bigger
                a[k] = aux[i++];
            }
        }
    }

    // Mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if(hi<=lo){return;}
        int mid = lo + (hi-lo)/2;
        sort(a,aux,lo, mid);
        sort(a,aux, mid+1, hi);
        merge(a,aux, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a,aux,0,a.length-1);
    }
}
