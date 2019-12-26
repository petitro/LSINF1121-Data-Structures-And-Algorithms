import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

public class StackTests {

    @Test
    public void firstTest() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.push(1);
        assertEquals(new Integer(1), stack.pop());
    }

    @Test
    public void secondTest() {
        Stack<Double> stack = new MyStack<Double>();
        stack.push(1.0);
        assertEquals(new Double(1.0), stack.pop());
    }

    @Test
    public void thirdTest(){
        Stack<Integer> stack = new MyStack<Integer>();

        stack.push(1);
        assertEquals(new Integer(1), stack.peek());

        stack.push(2);
        assertEquals(new Integer(2), stack.peek());

        assertEquals(false, stack.empty());

        assertEquals(new Integer(2), stack.pop());

        assertEquals(false, stack.empty());

        assertEquals(new Integer(1), stack.pop());

        assertEquals(true, stack.empty());

        //vide

        stack.push(1);
        assertEquals(new Integer(1), stack.peek());
        assertEquals(stack.peek(), stack.pop());

    }

    @Test
    public void fourthTest(){
        Stack<Integer> stack = new MyStack<Integer>();

        for(int i =0; i< Math.pow(2,16); i++) {
            stack.push(1);
            assertEquals(new Integer(1), stack.peek());
        }
        stack.push(2);
        assertEquals(new Integer(2), stack.peek());

        assertEquals(new Integer(2), stack.pop());
        assertEquals(new Integer(1), stack.pop());
    }

    @Test(expected = EmptyStackException.class)
    public void EmptyStackException() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.pop();
    }
    @Test(expected = EmptyStackException.class)
    public void EmptyStackException2() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.peek();
    }

    // Feel free to add tests in this class to debug your program




}

