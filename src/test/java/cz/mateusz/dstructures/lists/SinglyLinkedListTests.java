package cz.mateusz.dstructures.lists;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SinglyLinkedListTests {

    @Test
    public void addFirst() {
        SinglyLinkedList<String> sll = new SinglyLinkedList<>();
        sll.addFirst("Hello world");
        assertThat(sll.getFirst(), equalTo("Hello world"));
    }

    @Test
    public void addLast() {
        SinglyLinkedList<String> sll = new SinglyLinkedList<>();
        sll.addFirst("Hello world");
        sll.addLast("Bye world");
        assertThat(sll.getLast(), equalTo("Bye world"));
        assertThat(sll.getFirst(), equalTo("Hello world"));
    }

    @Test
    public void removeFirst() {
        SinglyLinkedList<String> sll = new SinglyLinkedList<>();
        sll.addFirst("Hello world");
        sll.removeFirst();
        assertThat(sll.getFirst(), nullValue());
    }
}
