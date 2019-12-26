
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class MyTests {
    @Test
    public void testNbIslandsExam() {
        int [][] tab = new int[][] {{1,3,3,1,3},
                                    {4,2,2,4,5},
                                    {4,4,1,4,2},
                                    {1,4,2,3,6},
                                    {1,1,1,6,3}};
        GlobalWarming g =  new GlobalWarmingImpl(tab,2);

        System.out.println("finish");
    }
}
