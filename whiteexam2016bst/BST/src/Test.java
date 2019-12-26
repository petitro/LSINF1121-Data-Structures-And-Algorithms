import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

import junit.framework.TestCase;

public class Test extends TestCase {
	
    protected void setUp() {}

    /**
     * Generates a random array of Integers, of size n
     */
    public static Integer[] randomArray(int n) {
        java.util.Random rand = new java.util.Random();
        Integer [] array = new Integer[n];
        Arrays.setAll(array, i -> rand.nextInt(1000000));
        return array;
    }

    /**
     * Verifies that values.ceil(where) == ceilFound
     * @param values
     * @param ceilFound
     * @param where
     */
    public static boolean verify(Integer[] values, Integer ceilFound, int where) {
    	// Let a real balanced tree for the Java STD lib do the work for us:
    	TreeSet<Integer> set = new TreeSet<Integer>();
    	Collections.addAll(set, values);
    	Integer ceil2 = set.ceiling(where);
    
    	if(ceilFound != null && ceil2 != null)
    		return ceilFound.equals(ceil2);
    	else
    		return ceilFound == ceil2;
    }
    
    @org.junit.Test
    public void testCeilOk() {
        for (int i = 100; i < 1000; i += 100) {
            Integer[] v = randomArray(i+1);
            Node root = new Node(v[0]);
            for(int j = 1; j < v.length; j++)
            	root.add(v[j]);
            for(int j = -200; j < 1000001; j += 1000) {
            	Integer ceil = Ceil.ceil(root, j);
            	if(!verify(v,ceil,j))
                    Ceil.ceil(root, j);
                assertTrue("correct ceiling value computed",verify(v,ceil,j));
            }
        }
    }
}