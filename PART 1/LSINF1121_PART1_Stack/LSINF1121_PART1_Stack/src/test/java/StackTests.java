import org.junit.Test;

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
        // ... TODO ...
    }

    // Feel free to add tests in this class to debug your program




}

