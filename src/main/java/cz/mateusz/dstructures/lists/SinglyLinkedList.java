package cz.mateusz.dstructures.lists;

import java.util.Objects;

public class SinglyLinkedList<ContentType> {

    private static class Node<ContentType> {
        private ContentType content;

        private Node next;

        public Node(ContentType content, Node next) {
            this(content);
            this.next = next;
        }

        public Node(ContentType content) {
            this.content = content;
        }

        public ContentType getContent() {
            return content;
        }

        public void setNext(Node next) {
            if(this == next)
                throw new IllegalArgumentException("Cannot reference to the same node");

        }

        public Node getNext() {
            return next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(content, node.content);
        }

        @Override
        public int hashCode() {
            return Objects.hash(content);
        }
    }

    private Node<ContentType> head;

    private Node<ContentType> tail;

    private int size = 0;

    public ContentType getFirst() {
        return head == null ? null : head.content;
    }

    public ContentType getLast() {
        return tail == null ? null : tail.content;
    }

    public int addFirst(ContentType content) {
        head = new Node<>(content, head);
        if(size == 0) {
            tail = head;
        }
        return size++;
    }

    public int addLast(ContentType content) {
        if(size == 0) {
            return addFirst(content);
        }
        else {
            Node<ContentType> newNode = new Node<>(content);
            newNode.next = null;
            tail.next = newNode;
            tail = newNode;
            return size++;
        }
    }

    public int removeFirst() {
        if(size == 0) {
            throw new UnsupportedOperationException("List is empty");
        }
        head = head.next;
        size--;
        if(size == 0) {
            tail = null;
        }
        return size;
    }
}
