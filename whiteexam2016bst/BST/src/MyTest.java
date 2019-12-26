import junit.framework.TestCase;

public class MyTest extends TestCase{
    @org.junit.Test
    public void testCeilOk() {
        assertTrue("not correct with no bst", Ceil.ceil(null,0)==null);
        Node tree = new Node(50);
        assertTrue("not correct with 1 element", Ceil.ceil(tree,0)==50);
        tree.add(40); tree.add(60);
        assertTrue("not correct with 3 elements", Ceil.ceil(tree,0)==40);
        assertTrue("not correct with 3 elements", Ceil.ceil(tree,40)==40);
        assertTrue("not correct with 3 elements", Ceil.ceil(tree,45)==50);
        assertTrue("not correct with 3 elements", Ceil.ceil(tree,65)==null);
        tree.add(20);tree.add(45);tree.add(55);tree.add(65);
        assertTrue("not correct with 3 plate", Ceil.ceil(tree,67)==null);
        assertTrue("not correct with 3 plate", Ceil.ceil(tree,64)==65);
        assertTrue("not correct with 3 plate", Ceil.ceil(tree,10)==20);
        assertTrue("not correct with 3 plate", Ceil.ceil(tree,48)==50);
    }
}
