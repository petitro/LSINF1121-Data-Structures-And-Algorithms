import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;


public class myTest {

    @Test
    public void firstTest() {
        CircularLinkedList<Integer> cliste = new CircularLinkedList<Integer>();
        assertEquals(0, cliste.size());
        assertEquals(true, cliste.isEmpty());
        cliste.enqueue(2);
        assertEquals(1, cliste.size());
        assertEquals(false, cliste.isEmpty());
        assertEquals(new Integer(2), (Integer) cliste.remove(0));
        assertEquals(0, cliste.size());
        assertEquals(true, cliste.isEmpty());
    }
    @Test
    public void secondTest() {
        CircularLinkedList<Integer> cliste = new CircularLinkedList<Integer>();
        for (int i =0; i<Math.pow(2,5);i++) {
            cliste.enqueue(i);
        }
        assertEquals((int)Math.pow(2,5), cliste.size());
        assertEquals(false, cliste.isEmpty());
        assertEquals(new Integer(0), (Integer) cliste.remove(0));

    }

    @Test
    public void iteratorTest() {
        CircularLinkedList<Integer> cliste = new CircularLinkedList<Integer>();
        int sz = 100000;
        for (int i = 0; i < sz/2; i++){
            cliste.enqueue(i);
        }
        cliste.enqueue(sz/2);
        for (int i = sz/2-1; i >0; i--){
            cliste.remove(i);
        }
        cliste.remove((cliste.size())-1);
        cliste.remove(0);
    }
}