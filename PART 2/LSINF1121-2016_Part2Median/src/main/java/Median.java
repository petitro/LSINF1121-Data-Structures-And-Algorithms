import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Median {

    public static int median(Vector a, int lo, int hi) {
        if(hi<=lo){return -1;}
        int size = hi-lo+1;

        int j = sort(a, 0, size-1, size);
        return a.get(j);
    }

    private static int sort(Vector a, int lo, int hi, int size){
        if (lo>hi){
            return -1;}
        int j=partition(a,lo,hi);

        if (j>(size-1)/2){
            j= sort(a,lo,j-1, size);
            return j;}
        else if (j<(size-1)/2){
            j = sort(a,j+1,hi, size);
            return j;}
        else{
            return j;} //j==(size-1)/2
    }

    private static int partition(Vector a, int lo, int hi){
        int i=lo;
        int j = hi+1;
        int v = a.get(lo);
        while (true){
            while(a.get(++i)<v){if(i==hi){break;}}//scan left
            while(a.get(--j)>v){if(j==lo){break;}}//scan right
            if(i>=j){break;}//everything is scan
            a.swap(i,j);//i too big and j too small
        }
        a.swap(lo, j);//placing the pivot att the right spot
        return j;//returning the pivot
    }
}