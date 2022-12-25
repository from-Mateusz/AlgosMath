package cz.mateusz.dstructures.lists;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CircularlyLinkedListTests {

    @Test
    public void shouldAddFirst() {
        CircularlyLinkedList<String> list = new CircularlyLinkedList<>();
        list.addFirst("Hello world");
        assertThat(list.getFirst(), equalTo("Hello world"));
        assertThat(list.getLast(), equalTo("Hello world"));
    }

    @Test
    public void shouldAddLast() {
        CircularlyLinkedList<String> list = new CircularlyLinkedList<>();
        list.addFirst("Hello world");
        list.addLast("Hello city");
        assertThat(list.getLast(), equalTo("Hello city"));
    }

    @Test
    public void shouldAddFirstWhenTheListIsNotEmpty() {
        CircularlyLinkedList<String> list = new CircularlyLinkedList<>();
        list.addFirst("Hello world");
        list.addFirst("Hello city");
        assertThat(list.getFirst(), equalTo("Hello city"));
        assertThat(list.getLast(), equalTo("Hello world"));
    }

    @Test
    public void shouldRemoveFirstElement() {
        CircularlyLinkedList<String> list = new CircularlyLinkedList<>();
        list.addFirst("Hello world");
        list.addFirst("Hello city");
        String element = list.removeFirst();
        assertThat(element, equalTo("Hello city"));
        assertThat(list.getFirst(), equalTo("Hello world"));
    }
}
