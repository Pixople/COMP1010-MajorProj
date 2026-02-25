import org.junit.*;
import static org.junit.Assert.*;

public class ExampleTest {

    // Test CREATING a single Example node
    @Test
    public void testCreateSingleExample() {
        Example ex = new Example("abc123", 6, null);
        assertEquals("abc123", ex.password);
        assertEquals(6, ex.length);
        assertNull(ex.next);
    }

    // Test LINKING multiple Example nodes
    @Test
    public void testLinkingExamples() {
        Example ex1 = new Example("first", 5, null);
        Example ex2 = new Example("second", 6, null);
        ex1.next = ex2;
        assertEquals("second", ex1.next.password);
        assertEquals(6, ex1.next.length);
    }

    // Edge case: Test Example with EMPTY password
    @Test
    public void testEmptyPassword() {
        Example ex = new Example("", 0, null);
        assertEquals("", ex.password);
        assertEquals(0, ex.length);
    }

    // Edge case: Test Example with NULL password
    @Test
    public void testNullPassword() {
        Example ex = new Example(null, 0, null);
        assertNull(ex.password);
        assertEquals(0, ex.length);
    }

    // Test traversing a LINKED LIST of Examples
    @Test
    public void testTraverseLinkedList() {
        Example ex1 = new Example("one", 3, null);
        Example ex2 = new Example("two", 3, null);
        Example ex3 = new Example("three", 5, null);
        ex1.next = ex2;
        ex2.next = ex3;

        int count = 0;
        Example curr = ex1;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        assertEquals(3, count);
    }

    // Test calculating TOTAL LENGTH recursively
    @Test
    public void testGetTotalLengthRecursive() {
        Example ex1 = new Example("a", 1, null);
        Example ex2 = new Example("bb", 2, null);
        Example ex3 = new Example("ccc", 3, null);
        ex1.next = ex2;
        ex2.next = ex3;
        int total = getTotalLength(ex1);
        assertEquals(6, total);
    }

    // Test CALCULATING count recursively
    @Test
    public void testGetCountRecursive() {
        Example ex1 = new Example("a", 1, null);
        Example ex2 = new Example("bb", 2, null);
        ex1.next = ex2;
        int count = getCount(ex1);
        assertEquals(2, count);
    }

    // Edge case: Test recursive methods with NULL head
    @Test
    public void testRecursiveMethodsNullHead() {
        assertEquals(0, getTotalLength(null));
        assertEquals(0, getCount(null));
    }

    // Edge case: Test Example with NEGATIVE length
    @Test
    public void testNegativeLength() {
        Example ex = new Example("bad", -5, null);
        assertEquals(-5, ex.length);
    }

    // Testing LONG linked list
    @Test
    public void testLongLinkedList() {
        Example head = new Example("start", 5, null);
        Example curr = head;
        int totalLen = 5;
        for (int i = 0; i < 50; i++) {
            Example next = new Example("pw" + i, i, null);
            curr.next = next;
            curr = next;
            totalLen += i;
        }
        assertEquals(51, getCount(head));
        assertEquals(totalLen, getTotalLength(head));
    }

    // HELPER methods (same as in VaultDriver) for recursive calculations
    private static int getTotalLength(Example e) {
        if (e == null) return 0;
        return e.length + getTotalLength(e.next);
    }

    private static int getCount(Example e) {
        if (e == null) return 0;
        return 1 + getCount(e.next);
    }
}
